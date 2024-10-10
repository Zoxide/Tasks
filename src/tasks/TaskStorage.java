package tasks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final String FILE_PATH = "C:\\Users\\zoxid\\Desktop\\Java\\Tasks\\src\\storage\\tasks.txt";

    /**
     * Saves a task to the storage file.
     *
     * @param task The task to be saved.
     */
    public void saveTask(final Task task) {
        try (final FileWriter writer = new FileWriter(TaskStorage.FILE_PATH, true)) {
            writer.write(task.toString() + "\n");
        } catch (final IOException e) {
            System.out.println("An error occurred while saving the task.");
            e.printStackTrace();
        }
    }

    /**
     * Reads all tasks from the storage file.
     *
     * @return A list of tasks read from the file.
     */
    public List<Task> readTasks() {
        final List<Task> tasks = new ArrayList<>();
        try (final BufferedReader reader = new BufferedReader(new FileReader(TaskStorage.FILE_PATH, StandardCharsets.UTF_8))) {
            String line;
            while (null != (line = reader.readLine())) {
                // Remove the curly braces and prepare the line for splitting
                final String[] parts = line.replace("Task{", "").replace("}", "").split(", ");
                if (5 == parts.length) {
                    // Remove single quotes and split by '=' to get the field value
                    final String name = parts[0].split("=")[1].replace("'", "").trim();
                    final String description = parts[1].split("=")[1].replace("'", "").trim();
                    final String status = parts[2].split("=")[1].replace("'", "").trim();
                    final String dueDate = parts[3].split("=")[1].replace("'", "").trim();
                    final String category = parts[4].split("=")[1].replace("'", "").trim();

                    // Add the task to the list
                    tasks.add(new Task(name, description, status, dueDate, category));
                } else {
                    System.out.println("Invalid task format: " + line);
                }
            }
        } catch (final IOException e) {
            System.out.println("An error occurred while reading tasks.");
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Updates the status of a task with the specified name.
     *
     * @param taskName The name of the task to update.
     * @param newStatus The new status to set for the task.
     */
    public void updateTaskStatus(final String taskName, final String newStatus) {
        final List<Task> tasks = this.readTasks();
        boolean taskFound = false;

        for (final Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                task.setStatus(newStatus);
                taskFound = true;
            }
        }

        if (taskFound) {
            try (final FileWriter writer = new FileWriter(TaskStorage.FILE_PATH, false)) {
                for (final Task task : tasks) {
                    writer.write(task + "\n");
                }
            } catch (final IOException e) {
                System.out.println("An error occurred while updating the task.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Task not found.");
        }
    }

    /**
     * Searches for tasks by name.
     *
     * @param name The name to search for.
     * @return A list of tasks that match the search criteria.
     */
    public List<Task> searchTasksByName(final String name) {
        final List<Task> tasks = this.readTasks();
        final List<Task> filteredTasks = new ArrayList<>();
        final String lowerCaseName = name.toLowerCase();

        for (final Task task : tasks) {
            if (task.getName().toLowerCase().contains(lowerCaseName)) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }

    /**
     * Searches for tasks by category.
     *
     * @param category The category to search for.
     * @return A list of tasks that match the search criteria.
     */
    public List<Task> searchTasksByCategory(final String category) {
        final List<Task> tasks = this.readTasks();
        final List<Task> filteredTasks = new ArrayList<>();
        final String lowerCaseCategory = category.toLowerCase();

        for (final Task task : tasks) {
            if (task.getCategory().toLowerCase().contains(lowerCaseCategory)) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }

    /**
     * Deletes a task by name.
     *
     * @param taskName The name of the task to delete.
     */
    public void deleteTaskByName(final String taskName) {
        final List<Task> tasks = this.readTasks();
        boolean taskFound = false;

        for (final Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                tasks.remove(task);
                taskFound = true;
                break;
            }
        }

        if (taskFound) {
            try (final FileWriter writer = new FileWriter(TaskStorage.FILE_PATH, false)) {
                for (final Task task : tasks) {
                    writer.write(task + "\n");
                }
                System.out.println("Task deleted successfully.");
            } catch (final IOException e) {
                System.out.println("An error occurred while deleting the task.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Task not found.");
        }
    }
}