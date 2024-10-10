package tasks;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class createTask {

    /**
     * Constructor that prompts the user to enter task details and saves the task.
     * It reads the task name, description, status, due date, and category from the user input.
     * If the status is not provided, it defaults to 'pending'.
     */
    public createTask() {
        final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        final TaskStorage storage = new TaskStorage();

        System.out.print("Enter task name: ");
        final String name = scanner.nextLine().trim();

        System.out.print("Enter task description: ");
        final String description = scanner.nextLine().trim();

        System.out.print("Enter task status (default is 'pending'): ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) {
            status = "pending";
        }

        System.out.print("Enter task due date (DD-MM-YYYY): ");
        final String dueDate = scanner.nextLine().trim();

        System.out.print("Enter task category: ");
        final String category = scanner.nextLine().trim();

        final Task task = new Task(name, description, status, dueDate, category);
        storage.saveTask(task);

        System.out.println("Task created and saved successfully!");
    }

    /**
     * The main method that creates an instance of createTask to prompt the user for task details.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args) {
        new createTask();
    }
}