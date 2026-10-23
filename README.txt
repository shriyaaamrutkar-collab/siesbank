========================================================================================
    STUDENT TIME MANAGEMENT APP - POMODORO TIMER (College Mini Project)
========================================================================================

PROJECT OVERVIEW:
-----------------
This is a complete Java Swing application that combines task management with a Pomodoro timer.
It's designed as a beginner-friendly project that demonstrates proper Object-Oriented
Programming (OOP) principles.

The application provides:
• Task management (Add, Delete, Mark Complete)
• Due date tracking
• 25-minute Pomodoro focus timer
• Clean, intuitive GUI using Java Swing


FILE STRUCTURE:
---------------
MINIPROJECT/
├── Task.java              - Stores individual task details
├── TaskManager.java       - Manages the collection of tasks
├── TimerManager.java      - Handles the Pomodoro timer logic
├── MainUI.java            - Main GUI class (entry point)
└── README.txt             - This file


CLASS DESCRIPTIONS:
-------------------

1. Task.java
   - Purpose: Represents a single task
   - Key Components:
     * taskId: Unique identifier for each task
     * taskName: Name/description of the task
     * dueDate: When the task is due
     * isCompleted: Whether the task is completed
   - Methods:
     * Constructors (2 overloaded versions)
     * Getters and Setters
     * toString() - Custom display format

2. TaskManager.java
   - Purpose: Manages all tasks (business logic)
   - Key Features:
     * Uses ArrayList to store tasks
     * addTask() - Add new task to list
     * deleteTask() - Remove task by index
     * markTaskCompleted() - Toggle task completion status
     * getAllTasks() - Retrieve all tasks
   - Design Pattern: Aggregation and Encapsulation

3. TimerManager.java
   - Purpose: Handles Pomodoro timer (25 minutes)
   - Key Features:
     * startTimer() - Begin countdown
     * stopTimer() - Pause the timer
     * resetTimer() - Reset to initial value
     * tick() - Decrease time by 1 second
     * getFormattedTime() - Display as MM:SS format
   - Default Duration: 25 minutes (customizable)

4. MainUI.java
   - Purpose: Complete GUI using Swing
   - Layout:
     * LEFT SIDE: Task management panel
       - Task list display
       - Add task input fields
       - Delete, Mark Complete, Clear All buttons
     * RIGHT SIDE: Timer panel
       - Large timer display (MM:SS format)
       - Start, Stop, Reset buttons
   - Features:
     * Real-time timer updates using Swing Timer
     * List selection and task operations
     * Confirmation dialogs for important actions
     * User-friendly error messages


HOW TO COMPILE AND RUN:
-----------------------

STEP 1: Open Command Prompt (Windows) or Terminal (Mac/Linux)

STEP 2: Navigate to the MINIPROJECT folder
        Command: cd "path\to\MINIPROJECT"
        Example: cd "C:\Users\shriy\OneDrive\Desktop\Shriyaa_java\MINIPROJECT"

STEP 3: Compile all Java files
        Command: javac *.java
        This will compile all .java files and create corresponding .class files

STEP 4: Run the application
        Command: java MainUI
        The GUI window will appear with the Time Management App

COMPLETE EXAMPLE (Windows):
---
C:\Users\shriy\OneDrive\Desktop> cd Shriyaa_java\MINIPROJECT
C:\Users\shriy\OneDrive\Desktop\Shriyaa_java\MINIPROJECT> javac *.java
C:\Users\shriy\OneDrive\Desktop\Shriyaa_java\MINIPROJECT> java MainUI
---


FEATURES AND HOW TO USE:
------------------------

1. ADDING A TASK:
   - Type the task name in the "Task:" field
   - (Optional) Enter a due date in the "Due Date:" field
   - Click "➕ Add Task" button
   - Task appears in the list below

2. MARKING A TASK COMPLETE:
   - Select a task from the list
   - Click "✓ Mark Complete" button
   - Task status changes from "○ TODO" to "✓ DONE"
   - Click again to mark as incomplete

3. DELETING A TASK:
   - Select a task from the list
   - Click "🗑 Delete" button
   - Task is removed from the list

4. CLEARING ALL TASKS:
   - Click "🔄 Clear All" button
   - Confirm the action in the dialog box
   - All tasks are removed

5. USING THE POMODORO TIMER:
   - Click "▶ Start" to begin the 25-minute countdown
   - Click "⏸ Stop" to pause the timer
   - Click "🔄 Reset" to reset timer back to 25:00
   - When timer finishes, an alert notification appears
   - Timer color changes to red when finished


OOP PRINCIPLES DEMONSTRATED:
-----------------------------

1. ENCAPSULATION:
   - Private variables with public getters/setters
   - Example: Task class variables are private, accessed via methods

2. ABSTRACTION:
   - TaskManager hides implementation details of task storage
   - TimerManager abstracts timer logic from UI

3. INHERITANCE:
   - Could be extended (e.g., create SpecialTask extending Task)

4. POLYMORPHISM:
   - Overloaded constructors in Task class
   - toString() override for custom display

5. COMPOSITION:
   - MainUI uses TaskManager and TimerManager
   - Demonstrates "has-a" relationship


CODE QUALITY FEATURES:
----------------------
✓ Well-structured with separate classes for different concerns
✓ Comprehensive comments explaining functionality
✓ Proper error handling with user-friendly messages
✓ Clean GUI layout with Swing best practices
✓ Method-level documentation (Javadoc style)
✓ Consistent naming conventions
✓ Proper encapsulation and access modifiers
✓ Event listeners for button interactions
✓ Real-time component updates


SYSTEM REQUIREMENTS:
--------------------
• Java 8 or higher
• No external libraries required (only standard Java Swing)
• Any operating system (Windows, Mac, Linux)


POSSIBLE ENHANCEMENTS:
----------------------
(For advanced users who want to expand this project)

1. Add multiple timers (break timers, custom time)
2. File I/O to save/load tasks
3. Sound notifications when timer completes
4. Color themes/dark mode
5. Task categories or tags
6. Simple persistence with text file storage
7. Recurring tasks
8. Statistics tracking
9. Keyboard shortcuts
10. Task priority levels


TROUBLESHOOTING:
----------------

Issue: "Error: cannot find symbol"
Solution: Make sure all 4 files are in the same directory before compilation

Issue: "Exception: AWT EventQueue" or GUI won't start
Solution: Ensure you're running: java MainUI (not with an argument)

Issue: Timer not updating
Solution: This shouldn't happen, but restart the application

Issue: Compilation works but nothing happens when running
Solution: Make sure you're running "java MainUI" from the correct directory


FOR STUDENTS - PROJECT SUBMISSION TIPS:
----------------------------------------
1. Keep this README with your code submission
2. Include screenshots of the working application
3. Explain what each class does in your project report
4. Mention the OOP concepts used (encapsulation, abstraction, etc.)
5. Describe how the GUI is organized (left/right panel layout)
6. Use this code as study material for understanding:
   - OOP principles
   - Swing GUI design
   - Event handling
   - ArrayList usage
   - Method documentation


CREDITS:
--------
Created as a beginner-friendly Java Swing project for college students.
Inspired by task management apps like TickTick and Notion.

========================================================================================
                            Happy Learning! 🚀
========================================================================================
