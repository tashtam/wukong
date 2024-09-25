package Wukong;

import Wukong.quiz.Q3;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LockTest {

    private Lock quizLock;
    private Lock keyLock;
    private Lock unlockedLock;
    private Inventory key;
    private Q3 quiz;
    private Scanner scanner;

    @Before
    public void setUp() {
        key = new Inventory("Key", "key", 0, 1, 1);
        InputStream input = new ByteArrayInputStream("4\n".getBytes()); // Simulating input
        scanner = new Scanner(input);
        quiz = new Q3(() -> System.out.println("Quiz quit action executed"));
        quizLock = new Lock(quiz, scanner);
        keyLock = new Lock(key);
        unlockedLock = new Lock(); // Default unlocked lock
    }

    @Test
    public void testQuizLockInitiallyLocked() {
        assertTrue(quizLock.isLocked());
    }

    @Test
    public void testKeyLockInitiallyLocked() {
        assertTrue(keyLock.isLocked());
    }

    @Test
    public void testUnlockedLockInitiallyUnlocked() {
        assertFalse(unlockedLock.isLocked());
    }

    @Test
    public void testUnlockWithCorrectQuizAnswer() {
        // Simulate user input for the correct answer
        InputStream input = new ByteArrayInputStream("B\n".getBytes());
        scanner = new Scanner(input);
        quizLock = new Lock(quiz, scanner); // Reinitialize with simulated input

        boolean result = quizLock.Unlock(null);
        assertFalse(result); // Expect the lock to be unlocked
        assertFalse(quizLock.isLocked()); // Ensure the lock is now unlocked
    }

    @Test
    public void testUnlockWithKey() {
        assertTrue(keyLock.isLocked());
        boolean result = keyLock.Unlock(key);
        assertFalse(result); // Expect the lock to be unlocked
        assertFalse(keyLock.isLocked()); // Ensure the lock is now unlocked
    }

    @Test
    public void testUnlockWithWrongKey() {
        Inventory wrongKey = new Inventory("Wrong Key", "wrong_key", 0, 1, 1);
        assertTrue(keyLock.isLocked());
        boolean result = keyLock.Unlock(wrongKey);
        assertTrue(result); // Expect the lock to remain locked
        assertTrue(keyLock.isLocked()); // Ensure the lock is still locked
    }

    @Test
    public void testQuitQuizAction() {
        InputStream input = new ByteArrayInputStream("quit question\n".getBytes());
        scanner = new Scanner(input);
        quizLock = new Lock(quiz, scanner); // Reinitialize with simulated input

        boolean result = quizLock.Unlock(null);
        assertTrue(result); // Expect the lock to remain locked
        assertTrue(quizLock.isLocked()); // Ensure the lock is still locked
    }
}
