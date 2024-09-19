package Wukong;

/**
 * handle the timing logic for the challenge.
 * Author: Ziying Ye
 */

public class TimeChallenge {
    private long startTime;
    private long duration; // Duration of the challenge in milliseconds

    public TimeChallenge(long duration) {
        this.duration = duration;
    }

    // Start the challenge timer
    public void start() {
        startTime = System.currentTimeMillis();
    }

    // Check if the challenge time is over
    public boolean isTimeOver() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime) >= duration;
    }
}
