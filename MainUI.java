/**
 * MainUI.java
 * Main GUI class for the Student Time Management App
 * Uses Java Swing to create the user interface
 * Layout: Left side (Task List), Right side (Timer)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainUI {
    // Main window
    private JFrame frame;

    // Task management components
    private TaskManager taskManager;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JTextField taskInputField;
    private JTextField dueDateField;

    // Timer management components
    private TimerManager timerManager;
    private JLabel timerDisplay;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private Timer swingTimer;

    // Constructor
    public MainUI() {
        // Initialize managers
        taskManager = new TaskManager();
        timerManager = new TimerManager();

        // Create main frame
        frame = new JFrame("Student Time Management App - Pomodoro Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        // Setup GUI components
        setupUI();

        frame.setVisible(true);
    }

    /**
     * Setup all UI components and layout
     */
    private void setupUI() {
        // Main container with two panels
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left Panel: Task List
        JPanel leftPanel = createTaskPanel();

        // Right Panel: Timer
        JPanel rightPanel = createTimerPanel();

        // Add panels to main panel
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        frame.add(mainPanel);
    }

    /**
     * Create the left panel for task management
     * @return JPanel containing task list and controls
     */
    private JPanel createTaskPanel() {
        JPanel taskPanel = new JPanel(new BorderLayout(5, 5));
        taskPanel.setBorder(BorderFactory.createTitledBorder("📋 Tasks"));

        // Title label
        JLabel titleLabel = new JLabel("Your Tasks:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Task list
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 12));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(300, 300));

        // Input panel for adding tasks
        JPanel inputPanel = createTaskInputPanel();

        // Top section with title
        JPanel topSection = new JPanel(new BorderLayout());
        topSection.add(titleLabel, BorderLayout.NORTH);

        // Combine top and list
        JPanel listSection = new JPanel(new BorderLayout(5, 5));
        listSection.add(topSection, BorderLayout.NORTH);
        listSection.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = createTaskButtonsPanel();

        // Combine all sections
        taskPanel.add(listSection, BorderLayout.CENTER);
        taskPanel.add(inputPanel, BorderLayout.NORTH);
        taskPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return taskPanel;
    }

    /**
     * Create input panel for adding new tasks
     * @return JPanel with input fields and add button
     */
    private JPanel createTaskInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add New Task"));

        // Task input
        JLabel taskLabel = new JLabel("Task:");
        taskInputField = new JTextField(15);
        JPanel taskFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        taskFieldPanel.add(taskLabel);
        taskFieldPanel.add(taskInputField);

        // Due date input
        JLabel dateLabel = new JLabel("Due Date:");
        dueDateField = new JTextField(15);
        JPanel dateFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dateFieldPanel.add(dateLabel);
        dateFieldPanel.add(dueDateField);

        // Add button
        JButton addButton = new JButton("➕ Add Task");
        addButton.setFont(new Font("Arial", Font.BOLD, 12));
        addButton.addActionListener(e -> addTaskAction());

        inputPanel.add(taskFieldPanel);
        inputPanel.add(dateFieldPanel);
        inputPanel.add(addButton);

        return inputPanel;
    }

    /**
     * Create buttons panel for task operations
     * @return JPanel with delete and mark complete buttons
     */
    private JPanel createTaskButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        // Delete button
        JButton deleteButton = new JButton("🗑 Delete");
        deleteButton.addActionListener(e -> deleteTaskAction());

        // Mark complete button
        JButton completeButton = new JButton("✓ Mark Complete");
        completeButton.addActionListener(e -> markTaskCompleteAction());

        // Clear all button
        JButton clearButton = new JButton("🔄 Clear All");
        clearButton.addActionListener(e -> clearAllTasksAction());

        buttonsPanel.add(deleteButton);
        buttonsPanel.add(completeButton);
        buttonsPanel.add(clearButton);

        return buttonsPanel;
    }

    /**
     * Create the right panel for Pomodoro timer
     * @return JPanel containing timer display and controls
     */
    private JPanel createTimerPanel() {
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createTitledBorder("⏱ Pomodoro Timer"));
        timerPanel.setPreferredSize(new Dimension(200, 500));

        // Timer display
        timerDisplay = new JLabel(timerManager.getFormattedTime());
        timerDisplay.setFont(new Font("Arial", Font.BOLD, 48));
        timerDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        timerDisplay.setForeground(new Color(0, 100, 200));
        timerDisplay.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Info label
        JLabel infoLabel = new JLabel("<html>25-Minute<br>Focus Session</html>");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Timer buttons panel
        JPanel timerButtonsPanel = createTimerButtonsPanel();

        // Combine sections
        JPanel topSection = new JPanel(new BorderLayout());
        topSection.add(infoLabel, BorderLayout.NORTH);
        topSection.add(timerDisplay, BorderLayout.SOUTH);

        timerPanel.add(topSection, BorderLayout.NORTH);
        timerPanel.add(timerButtonsPanel, BorderLayout.CENTER);

        // Initialize Swing Timer for updating display
        initializeSwingTimer();

        return timerPanel;
    }

    /**
     * Create timer control buttons
     * @return JPanel with start, stop, reset buttons
     */
    private JPanel createTimerButtonsPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Start button
        startButton = new JButton("▶ Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBackground(new Color(0, 180, 0));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> startTimerAction());

        // Stop button
        stopButton = new JButton("⏸ Stop");
        stopButton.setFont(new Font("Arial", Font.BOLD, 14));
        stopButton.setBackground(new Color(255, 100, 0));
        stopButton.setForeground(Color.WHITE);
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> stopTimerAction());

        // Reset button
        resetButton = new JButton("🔄 Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(100, 100, 100));
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(e -> resetTimerAction());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        return buttonPanel;
    }

    /**
     * Initialize Swing Timer for updating timer display every second
     */
    private void initializeSwingTimer() {
        swingTimer = new Timer(1000, e -> {
            // Update timer
            timerManager.tick();
            timerDisplay.setText(timerManager.getFormattedTime());

            // Change color if finished
            if (timerManager.isFinished()) {
                timerDisplay.setForeground(new Color(200, 0, 0));
                JOptionPane.showMessageDialog(frame, "Time's up! Good work! 🎉", 
                    "Timer Finished", JOptionPane.INFORMATION_MESSAGE);
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }

            // Update button states
            startButton.setEnabled(!timerManager.isRunning());
            stopButton.setEnabled(timerManager.isRunning());
        });
    }

    // ==================== Action Methods ====================

    /**
     * Action handler for adding a task
     */
    private void addTaskAction() {
        String taskName = taskInputField.getText().trim();
        String dueDate = dueDateField.getText().trim();

        if (taskName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a task name!", 
                "Empty Task", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (dueDate.isEmpty()) {
            dueDate = "No date";
        }

        taskManager.addTask(taskName, dueDate);
        taskInputField.setText("");
        dueDateField.setText("");
        updateTaskListDisplay();
    }

    /**
     * Action handler for deleting a task
     */
    private void deleteTaskAction() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete!", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        taskManager.deleteTask(selectedIndex);
        updateTaskListDisplay();
    }

    /**
     * Action handler for marking task as complete
     */
    private void markTaskCompleteAction() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to mark complete!", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Task task = taskManager.getTaskByIndex(selectedIndex);
        task.setCompleted(!task.isCompleted());
        updateTaskListDisplay();
    }

    /**
     * Action handler for clearing all tasks
     */
    private void clearAllTasksAction() {
        int option = JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to delete all tasks?", 
            "Confirm Clear", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            taskManager.clearAllTasks();
            updateTaskListDisplay();
        }
    }

    /**
     * Action handler for starting timer
     */
    private void startTimerAction() {
        timerManager.startTimer();
        swingTimer.start();
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        timerDisplay.setForeground(new Color(0, 100, 200));
    }

    /**
     * Action handler for stopping timer
     */
    private void stopTimerAction() {
        timerManager.stopTimer();
        swingTimer.stop();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    /**
     * Action handler for resetting timer
     */
    private void resetTimerAction() {
        timerManager.resetTimer();
        swingTimer.stop();
        timerDisplay.setText(timerManager.getFormattedTime());
        timerDisplay.setForeground(new Color(0, 100, 200));
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    /**
     * Update the task list display
     */
    private void updateTaskListDisplay() {
        listModel.clear();
        List<Task> tasks = taskManager.getAllTasks();
        for (Task task : tasks) {
            listModel.addElement(task.toString());
        }
    }

    /**
     * Main method to run the application
     */
    public static void main(String[] args) {
        // Ensure GUI operations run on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new MainUI());
    }
}
