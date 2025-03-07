package Rules;

import java.io.*;

final class User implements Serializable {
    private static final long serialVersionUID = 1L; // Best practice for serialization

    private String name;
    private transient String password; // transient: won't be serialized

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Correct serialization method
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Custom encryption (example) before writing the password
        out.writeObject(password != null ? password.hashCode() : null);
    }

    // Correct deserialization method
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Read and restore password (example: reset to default)
        this.password = "defaultPassword";
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', password='" + password + "'}";
    }
}