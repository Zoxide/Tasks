package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation {

    /**
     * Validates integer input within a specified range.
     *
     * @param scanner The Scanner object to read user input.
     * @param min The minimum valid value.
     * @param max The maximum valid value.
     * @param errorMessage The error message to display for invalid input.
     * @return The validated integer input.
     */
    public static int getValidatedChoice(final Scanner scanner, final int min, final int max, final String errorMessage) {
        int choice = -1;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.println(menuUtils.RED + errorMessage + menuUtils.RESET);
                }
            } catch (final InputMismatchException e) {
                System.out.println(menuUtils.RED + "Invalid input! Please enter a number." + menuUtils.RESET);
                scanner.next(); // clear the invalid input
            }
        }
        return choice;
    }

    /**
     * Validates non-empty string input.
     *
     * @param scanner The Scanner object to read user input.
     * @param promptMessage The prompt message to display to the user.
     * @return The validated non-empty string input.
     */
    public static String getValidatedString(final Scanner scanner, final String promptMessage) {
        String input;
        while (true) {
            System.out.print(promptMessage);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            } else {
                System.out.println(menuUtils.RED + "Input cannot be empty!" + menuUtils.RESET);
            }
        }
        return input;
    }
}