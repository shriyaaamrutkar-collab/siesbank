/**
 * TaskManager.java
 * Manages the collection of tasks - add, delete, update, retrieve
 * This class demonstrates the concept of aggregation and encapsulation
 */

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int nextTaskId;

    // Constructor: Initialize empty task list
    public TaskManager() {
        tasks = new ArrayList<>();
        nextTaskId = 1;
    }

    /**
     * Add a new task to the list
     * @param taskName Name of the task
     * @param dueDate Due date of the task
     */
    public void addTask(String taskName, String dueDate) {
        if (taskName.isEmpty()) {
            System.out.println("Task name cannot be empty!");
            return;
        }
        Task newTask = new Task(nextTaskId, taskName, dueDate, false);
        tasks.add(newTask);
        nextTaskId++;
        System.out.println("Task added: " + taskName);
    }

    /**
     * Delete a task by its index
     * @param index Index of the task in the list
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            String deletedTask = tasks.get(index).getTaskName();
            tasks.remove(index);
            System.out.println("Task deleted: " + deletedTask);
        } else {
            System.out.println("Invalid task index!");
        }
    }

    /**
     * Mark a task as completed/incomplete
     * @param index Index of the task
     * @param completed true if completed, false otherwise
     */
    public void markTaskCompleted(int index, boolean completed) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted(completed);
            System.out.println("Task status updated");
        } else {
            System.out.println("Invalid task index!");
        }
    }

    /**
     * Get all tasks as a list
     * @return List of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);  // Return a copy to maintain encapsulation
    }

    /**
     * Get task count
     * @return Number of tasks in the list
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Get a specific task by index
     * @param index Index of the task
     * @return Task at the given index
     */
    public Task getTaskByIndex(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Clear all tasks
     */
    public void clearAllTasks() {
        tasks.clear();
        System.out.println("All tasks cleared!");
    }
}
