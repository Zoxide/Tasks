package auth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthStorage {

    private static final String FILE_PATH = "C:\\Users\\zoxid\\Desktop\\Java\\Tasks\\src\\storage\\users.txt";

    /**
     * Saves user details to the storage file.
     *
     * @param username The username to be saved.
     * @param password The password to be saved.
     */
    public void saveUserDetails(final String username, final String password) {
        try (final FileWriter writer = new FileWriter(AuthStorage.FILE_PATH, true)) {
            writer.write("Username: " + username + ", Password: " + password + "\n");
        } catch (final IOException e) {
            System.out.println("An error occurred while saving user details.");
            e.printStackTrace();
        }
    }

    /**
     * Reads all user details from the storage file.
     *
     * @return A string containing all user details.
     */
    public String readUserDetails() {
        final StringBuilder userDetails = new StringBuilder();
        try (final BufferedReader reader = new BufferedReader(new FileReader(AuthStorage.FILE_PATH, StandardCharsets.UTF_8))) {
            String line;
            while (null != (line = reader.readLine())) {
                userDetails.append(line).append("\n");
            }
        } catch (final IOException e) {
            System.out.println("An error occurred while reading user details.");
            e.printStackTrace();
        }
        return userDetails.toString();
    }

    /**
     * Checks if a username is already taken.
     *
     * @param username The username to check.
     * @return True if the username is taken, false otherwise.
     */
    public boolean isUsernameTaken(final String username) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(AuthStorage.FILE_PATH, StandardCharsets.UTF_8))) {
            String line;
            while (null != (line = reader.readLine())) {
                if (line.contains("Username: " + username + ",")) {
                    return true;
                }
            }
        } catch (final IOException e) {
            System.out.println("An error occurred while checking username.");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Validates user credentials.
     *
     * @param username The username to validate.
     * @param password The password to validate.
     * @return True if the credentials are valid, false otherwise.
     */
    public boolean validateUser(final String username, final String password) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(AuthStorage.FILE_PATH, StandardCharsets.UTF_8))) {
            String line;
            while (null != (line = reader.readLine())) {
                if (line.equals("Username: " + username + ", Password: " + password)) {
                    return true;
                }
            }
        } catch (final IOException e) {
            System.out.println("An error occurred while validating user.");
            e.printStackTrace();
        }
        return false;
    }
}