package utils;

import tasks.Task;
import tasks.TaskStorage;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class menuUtils {
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    private static final int DELAY_MS = 2000; // 2 seconds delay

    public static void displayMainMenu() {
        System.out.println(CYAN + "-------------------------------------" + RESET);
        System.out.println(CYAN + " Main Menu " + RESET);
        System.out.println(CYAN + "-------------------------------------" + RESET);
        System.out.println(GREEN + "1. Register" + RESET);
        System.out.println(GREEN + "2. Login" + RESET);
        System.out.print(YELLOW + "Enter your choice: " + RESET);
    }

    public static void displayRegisterMenu() {
        System.out.println(CYAN + "-------------------------------------" + RESET);
        System.out.println(CYAN + " Registration Page " + RESET);
        System.out.println(CYAN + "-------------------------------------" + RESET);
    }

    public static void displayLoginMenu() {
        System.out.println(CYAN + "-------------------------------------" + RESET);
        System.out.println(CYAN + " Login Page " + RESET);
        System.out.println(CYAN + "-------------------------------------" + RESET);
    }

    public static void displayMainOptions() {
        final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        int choice;

        do {
            System.out.println(menuUtils.CYAN + "-------------------------------------" + menuUtils.RESET);
            System.out.println(menuUtils.CYAN + " Main Menu " + menuUtils.RESET);
            System.out.println(menuUtils.CYAN + "-------------------------------------" + menuUtils.RESET);
            System.out.println(menuUtils.GREEN + "1. Create Task" + menuUtils.RESET);
            System.out.println(menuUtils.GREEN + "2. Search Tasks" + menuUtils.RESET);
            System.out.println(menuUtils.GREEN + "3. Update Task Status" + menuUtils.RESET);
            System.out.println(menuUtils.GREEN + "4. Delete Task" + menuUtils.RESET);
            System.out.println(menuUtils.GREEN + "5. View All Tasks" + menuUtils.RESET);
            System.out.println(menuUtils.GREEN + "6. Logout" + menuUtils.RESET);
            System.out.print(menuUtils.YELLOW + "Enter your choice: " + menuUtils.RESET);

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> new tasks.createTask();
                    case 2 -> menuUtils.searchTasks();
                    case 3 -> menuUtils.updateTaskStatus();
                    case 4 -> menuUtils.deleteTask();
                    case 5 -> menuUtils.viewAllTasks();
                    case 6 -> System.out.println(menuUtils.GREEN + "Logged out." + menuUtils.RESET);
                    default -> System.out.println(menuUtils.RED + "Invalid choice! Please select a valid option." + menuUtils.RESET);
                }
            } else {
                System.out.println(menuUtils.RED + "Invalid input! Please enter a number between 1 and 6." + menuUtils.RESET);
                scanner.next();
                choice = -1;
            }

            // Add delay before redisplaying the menu
            try {
                Thread.sleep(DELAY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(menuUtils.RED + "An error occurred while waiting." + menuUtils.RESET);
            }

        } while (6 != choice);
    }

    private static void viewAllTasks() {
        TaskStorage storage = new TaskStorage();
        List<Task> tasks = storage.readTasks();

        if (tasks.isEmpty()) {
            System.out.println(RED + "No tasks found." + RESET);
        } else {
            displayTasks(tasks, "All Tasks");
        }

        // Add delay before redisplaying the menu
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(RED + "An error occurred while waiting." + RESET);
        }
    }

    private static void updateTaskStatus() {
        Scanner scanner = new Scanner(System.in);
        TaskStorage storage = new TaskStorage();

        System.out.print(YELLOW + "Enter task name to update: " + RESET);
        String taskName = scanner.nextLine().trim();

        System.out.print(YELLOW + "Enter new status (In Progress/Complete): " + RESET);
        String newStatus = scanner.nextLine().trim();

        if ("In Progress".equalsIgnoreCase(newStatus) || "Complete".equalsIgnoreCase(newStatus)) {
            storage.updateTaskStatus(taskName, newStatus);
            System.out.println(GREEN + "Task status updated successfully!" + RESET);
        } else {
            System.out.println(RED + "Invalid status! Please enter either 'In Progress' or 'Complete'." + RESET);
        }

        // Add delay before redisplaying the menu
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(RED + "An error occurred while waiting." + RESET);
        }
    }

    private static void searchTasks() {
        Scanner scanner = new Scanner(System.in);
        TaskStorage storage = new TaskStorage();

        System.out.println(YELLOW + "Search by:" + RESET);
        System.out.println(GREEN + "1. Name" + RESET);
        System.out.println(GREEN + "2. Category" + RESET);
        System.out.print(YELLOW + "Enter your choice: " + RESET);

        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Task> filteredTasks = switch (searchChoice) {
            case 1 -> {
                System.out.print(YELLOW + "Enter task name to search: " + RESET);
                String name = scanner.nextLine().trim();
                yield storage.searchTasksByName(name);
            }
            case 2 -> {
                System.out.print(YELLOW + "Enter category to search: " + RESET);
                String category = scanner.nextLine().trim();
                yield storage.searchTasksByCategory(category);
            }
            default -> {
                System.out.println(RED + "Invalid choice! Please select a valid option." + RESET);
                yield List.of();
            }
        };

        if (filteredTasks.isEmpty()) {
            System.out.println(RED + "No tasks found." + RESET);
        } else {
            displayTasks(filteredTasks, "Search Results");
        }

        // Add delay before redisplaying the menu
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(RED + "An error occurred while waiting." + RESET);
        }
    }

    private static void deleteTask() {
        Scanner scanner = new Scanner(System.in);
        TaskStorage storage = new TaskStorage();

        System.out.print(YELLOW + "Enter task name to delete: " + RESET);
        String taskName = scanner.nextLine().trim();

        storage.deleteTaskByName(taskName);

        // Add delay before redisplaying the menu
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(RED + "An error occurred while waiting." + RESET);
        }

        displayMainMenu();
    }

    private static void displayTasks(List<Task> tasks, String title) {
        System.out.println(CYAN + "-------------------------------------" + RESET);
        System.out.println(CYAN + " " + title + " " + RESET);
        System.out.println(CYAN + "-------------------------------------" + RESET);
        for (Task task : tasks) {
            System.out.println(GREEN + "Name: " + RESET + task.getName());
            System.out.println(GREEN + "Description: " + RESET + task.getDescription());
            System.out.println(GREEN + "Status: " + RESET + task.getStatus());
            System.out.println(GREEN + "Due Date: " + RESET + task.getDueDate());
            System.out.println(GREEN + "Category: " + RESET + task.getCategory());
            System.out.println(CYAN + "-------------------------------------" + RESET);
        }
    }
}