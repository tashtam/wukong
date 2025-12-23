package Wukong;

import Wukong.quiz.Quiz;
import java.util.Scanner;

/**
 * @author Tianfa Zhu
 *
 * This class represents a lock mechanism that can either be unlocked using a key item or by solving a quiz.
 * The lock can also be instantiated as unlocked by default.
 */
public class Lock {
    private Quiz quiz;
    private Inventory key;
    private boolean locked;
    private Scanner keyBoard;

    /**
     * Constructs a lock that requires a quiz to be solved for unlocking.
     *
     * @param quiz     The quiz that must be completed to unlock.
     * @param keyBoard The scanner used to input answers for the quiz.
     */
    public Lock(Quiz quiz, Scanner keyBoard) {
        this.keyBoard = keyBoard;
        this.quiz = quiz;
        locked = true;
    }

    /**
     * Constructs a lock that requires a specific key item to unlock.
     *
     * @param key The inventory item that acts as the key to unlock.
     */
    public Lock(Inventory key) {
        this.key = key;
        locked = true;
    }

    /**
     * Constructs a lock that is unlocked by default.
     */
    public Lock() {
        locked = false;
    }

    /**
     * Gets the quiz associated with the lock.
     *
     * @return The quiz needed to unlock.
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * Sets the quiz associated with the lock.
     *
     * @param quiz The quiz to be set for the lock.
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Checks whether the lock is currently locked.
     *
     * @return true if the lock is locked, false otherwise.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets the lock's locked state.
     *
     * @param locked The locked state to be set (true for locked, false for unlocked).
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Attempts to unlock the lock either by using the provided key or solving the quiz.
     *
     * @param key The key item to attempt unlocking with.
     * @return true if the lock remains locked, false if it was successfully unlocked.
     */
    public Boolean Unlock(Inventory key) {
        boolean shouldToggleLock = (this.key == null && quiz.start(this.keyBoard)) || (this.key != null && this.key.equals(key));
        if (shouldToggleLock) {
            locked = !locked;
        }
        return locked;
    }
}
