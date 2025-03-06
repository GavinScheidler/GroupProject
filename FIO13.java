/*
FIO13-J Example: Do not log sensitive information outside a trust boundary.
Author: Dane Iwema
*/
import java.util.logging.*;

public class FIO13 {
    private static final Logger logger = Logger.getLogger(FIO13.class.getName());

    public static void main(String[] args) {
        configureLogger();

        // Example: Logging an authentication attempt without exposing credentials
        authenticateUser("john_doe", "superSecurePassword123");
    }

    private static void configureLogger() {
        // Set all handlers to only allow INFO level and higher by default
        for (Handler handler : logger.getHandlers()) {
            handler.setLevel(Level.INFO);
        }

        // Adding a ConsoleHandler for demonstration
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);

        // Set logger level (FINEST messages won't be printed by default)
        logger.setLevel(Level.ALL);
    }

    private static void authenticateUser(String username, String password) {
        // Do not log passwords or sensitive user information!
        logger.info("User authentication attempt: " + username);

        try {
            if (validateCredentials(username, password)) {
                logger.info("Authentication successful for user: " + username);
            } else {
                logger.warning("Authentication failed for user: " + username);
            }
        } catch (SecurityException e) {
            // Handle the exception safely without exposing details
            logger.severe("Security exception occurred during authentication: " + sanitizeException(e));
        }
    }

    private static boolean validateCredentials(String username, String password) {
        // Simulated authentication logic (never store or log plain-text passwords)
        return "john_doe".equals(username) && "correctPassword".equals(password);
    }

    private static String sanitizeException(Exception e) {
        // Return a general error message instead of logging full stack traces with sensitive data
        return "An unexpected security issue occurred. Contact support if the issue persists.";
    }
}