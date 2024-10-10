package auth;

import utils.menuUtils;
import utils.InputValidation;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Register {

    Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    AuthStorage storage = new AuthStorage();

    /**
     * Constructor that handles the user registration process.
     * It displays the registration menu, prompts the user for a username and password,
     * checks if the username is already taken, and saves the user details if valid.
     * If registration is successful, it redirects to the login process.
     */
    public Register() {
        menuUtils.displayRegisterMenu();

        final String username = InputValidation.getValidatedString(this.scanner, menuUtils.YELLOW + "Enter a username: " + menuUtils.RESET);

        if (this.storage.isUsernameTaken(username)) {
            System.out.println(menuUtils.RED + "Username is already taken. Please choose another one." + menuUtils.RESET);
            return;
        }

        final String password = InputValidation.getValidatedString(this.scanner, menuUtils.YELLOW + "Enter a password: " + menuUtils.RESET);

        this.storage.saveUserDetails(username, password);

        System.out.println(menuUtils.GREEN + "Registration successful! You can now login." + menuUtils.RESET);
        new Login(); // Automatically redirects to login
    }
}