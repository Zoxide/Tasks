package auth;

import utils.menuUtils;
import utils.InputValidation;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Login {

    Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    AuthStorage storage = new AuthStorage();

    /**
     * Constructor that handles the user login process.
     * It displays the login menu, prompts the user for a username and password,
     * and validates the user credentials. If the credentials are valid,
     * it displays a welcome message and the main options' menu.
     * Otherwise, it displays an error message.
     */
    public Login() {
        menuUtils.displayLoginMenu();

        final String username = InputValidation.getValidatedString(this.scanner, menuUtils.YELLOW + "Enter your username: " + menuUtils.RESET);
        final String password = InputValidation.getValidatedString(this.scanner, menuUtils.YELLOW + "Enter your password: " + menuUtils.RESET);

        if (this.storage.validateUser(username, password)) {
            System.out.println(menuUtils.GREEN + "Login successful! Welcome, " + username + "!" + menuUtils.RESET);
            menuUtils.displayMainOptions();
        } else {
            System.out.println(menuUtils.RED + "Invalid username or password." + menuUtils.RESET);
        }
    }
}