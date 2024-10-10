package main;

import auth.Login;
import auth.Register;
import utils.InputValidation;
import utils.menuUtils;

import java.util.Scanner;

public class Main {

    /**
     * The main method that runs the application.
     * It continuously displays the main menu and handles user input for registration and login.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Display the main menu
                menuUtils.displayMainMenu();

                // Get the user's choice and validate it
                int choice = InputValidation.getValidatedChoice(scanner, 1, 2, "Please select 1 for Register or 2 for Login.");

                // Handle the user's choice
                switch (choice) {
                    case 1 -> new Register(); // Register
                    case 2 -> new Login();    // Login
                    default -> System.out.println(menuUtils.RED + "Invalid choice! Please try again." + menuUtils.RESET);
                }
            } catch (Exception e) {
                // Handle any exceptions that occur
                System.out.println(menuUtils.RED + "An error occurred: " + e.getMessage() + menuUtils.RESET);
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }
}