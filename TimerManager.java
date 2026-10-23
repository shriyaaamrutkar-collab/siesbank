/**
 * TimerManager.java
 * Manages the Pomodoro timer (25-minute countdown timer)
 * Handles starting, stopping, and time updates
 */

public class TimerManager {
    // Constants
    private static final int POMODORO_DURATION_MINUTES = 25;
    private static final int TOTAL_SECONDS = POMODORO_DURATION_MINUTES * 60;

    // Instance variables
    private int remainingSeconds;
    private boolean isRunning;
    private int totalSeconds;

    // Constructor
    public TimerManager() {
        this.totalSeconds = TOTAL_SECONDS;
        this.remainingSeconds = TOTAL_SECONDS;
        this.isRunning = false;
    }

    /**
     * Start the timer
     */
    public void startTimer() {
        isRunning = true;
    }

    /**
     * Stop the timer (pause)
     */
    public void stopTimer() {
        isRunning = false;
    }

    /**
     * Reset the timer to initial value
     */
    public void resetTimer() {
        isRunning = false;
        remainingSeconds = totalSeconds;
    }

    /**
     * Tick the timer - decrease remaining time by 1 second
     * Called periodically by a swing timer
     */
    public void tick() {
        if (isRunning && remainingSeconds > 0) {
            remainingSeconds--;
        }
        // Stop timer when it reaches 0
        if (remainingSeconds == 0) {
            isRunning = false;
        }
    }

    /**
     * Check if timer is running
     * @return true if timer is running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Get remaining time as formatted string (MM:SS)
     * @return Formatted time string
     */
    public String getFormattedTime() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Get remaining seconds
     * @return Remaining seconds
     */
    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    /**
     * Check if timer has finished
     * @return true if time is 0, false otherwise
     */
    public boolean isFinished() {
        return remainingSeconds == 0;
    }

    /**
     * Set custom duration (for flexibility)
     * @param minutes Duration in minutes
     */
    public void setDuration(int minutes) {
        this.totalSeconds = minutes * 60;
        resetTimer();
    }
}
