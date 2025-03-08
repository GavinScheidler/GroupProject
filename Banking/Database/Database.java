package Banking.Database;

import Banking.Model.User;
import Banking.Model.Account.Account;
import Banking.Model.Account.CheckingAccount;
import Banking.Model.Account.SavingsAccount;

import java.io.*;

/**
 * This class is used for any interaction with the text file database
 * the database is set up where each line is like a key for a hashtable.
 * What ever the line holds is the element,
 * 
 * @author Dane Iwema
 */
public class Database {
    private static final String FILE_NAME = "database.txt";

    /**
     * This method will rewrite the data at the postion of the
     * User given only if the username in the spot is empty or matching
     * 
     * @param user a User object you want to save to the database
     * @return true if the save was successfull and false if it was not successfull
     */
    public static boolean setUser(User user) {
        boolean worked = false;
        //FIO02-J: Detect and handle file-related errors used right here
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            StringBuilder line = new StringBuilder();

            // Format: username,password,name
            line.append(user.getUsername()).append(",")
                .append(user.getPassword()).append(",")
                .append(user.getName());

            // Append accounts (if any)
            for (Account account : user.getAccounts()) {
                if (account instanceof CheckingAccount) {
                    line.append(",Checking,").append(account.getBalance());
                } else if (account instanceof SavingsAccount) {
                    SavingsAccount savings = (SavingsAccount) account;
                    line.append(",Savings,").append(savings.getBalance())
                        .append(",").append(savings.getInterestRate());
                }
            }

            writer.write(line.toString());
            writer.newLine(); // Move to the next line

            //FIO14-J: Perform proper cleanup at program termination 
            writer.close();

            worked = true;
        } catch (IOException e) {
            System.err.println("Error writing to database: " + e.getMessage());
        }
        return worked;
    }


    /**
     * This method will pull the user with
     * the matching username and password with the given
     * user name and password.
     * 
     * @param username string
     * @param password string
     * @return a user object with matching credentials
     */
    public static User getUser(String username, String password) {
    File databaseFile = new File(FILE_NAME);
    File tempFile = new File("temp_database.txt");

    User foundUser = null;
    boolean userRemoved = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(databaseFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue; // Invalid format, skip

            String storedUsername = parts[0];
            String storedPassword = parts[1];
            String name = parts[2];

            if (!userRemoved && storedUsername.equals(username) && storedPassword.equals(password)) {
                // Found the user! Create the User object
                foundUser = new User(username, name, password);
                int index = 3;

                while (index < parts.length) {
                    String accountType = parts[index];
                    double balance = Double.parseDouble(parts[index + 1]);
                    index += 2;

                    if (accountType.equals("Checking")) {
                        foundUser.addCheckingAccount(balance);
                    } else if (accountType.equals("Savings")) {
                        double interestRate = Double.parseDouble(parts[index]);
                        foundUser.addSavingAccount(balance, interestRate);
                        index++;
                    }
                }

                userRemoved = true; // Mark the user as removed from the database
                continue; // Skip writing this user back to the file
            }

            // Write all other users back to the file
            writer.write(line);
            writer.newLine();
            //FIO14-J: Perform proper cleanup at program termination 
            writer.close();
        }
        //FIO14-J: Perform proper cleanup at program termination 
        reader.close();
    } catch (IOException e) {
        System.err.println("Error processing database: " + e.getMessage());
        return null;
    }

    // Replace old database with updated one
    if (!databaseFile.delete() || !tempFile.renameTo(databaseFile)) {
        System.err.println("Error updating database file.");
        return null;
    }

    return foundUser; // Return the found user or null if not found
}
}
