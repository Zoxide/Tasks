package tasks;

import java.io.Serializable;

/**
 * Represents a task with a name, description, status, due date, and category.
 * Implements Serializable for object serialization.
 */
public class Task implements Serializable {
    private final String name;
    private final String description;
    private String status;
    private final String dueDate;
    private final String category;

    /**
     * Constructs a new Task with the specified details.
     *
     * @param name The name of the task.
     * @param description The description of the task.
     * @param status The status of the task.
     * @param dueDate The due date of the task.
     * @param category The category of the task.
     */
    public Task(final String name, final String description, final String status, final String dueDate, final String category) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.category = category;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status The new status of the task.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Gets the due date of the task.
     *
     * @return The due date of the task.
     */
    public String getDueDate() {
        return this.dueDate;
    }

    /**
     * Gets the category of the task.
     *
     * @return The category of the task.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "Task{" +
                "name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", status='" + this.status + '\'' +
                ", dueDate='" + this.dueDate + '\'' +
                ", category='" + this.category + '\'' +
                '}';
    }
}