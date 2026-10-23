/**
 * Task.java
 * Represents a single task with its details
 * This class follows the principle of encapsulation
 */

public class Task {
    // Private variables - encapsulation
    private int taskId;
    private String taskName;
    private String dueDate;
    private boolean isCompleted;

    // Constructor 1: Basic constructor with task name
    public Task(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.dueDate = "No date";
        this.isCompleted = false;
    }

    // Constructor 2: Full constructor with all details
    public Task(int taskId, String taskName, String dueDate, boolean isCompleted) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    // Getters
    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setters
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Override toString for better display
    @Override
    public String toString() {
        String status = isCompleted ? "✓ DONE" : "○ TODO";
        return String.format("[%s] %s (Due: %s)", status, taskName, dueDate);
    }
}
