package Wukong;

import Wukong.quiz.Quiz;

import java.util.Scanner;

public class Lock {
    private Quiz Quiz;
    private Inventory key;
    private boolean locked;
    private Scanner keyBoard;

    public Lock(Quiz quiz, Scanner keyBoard) {
        this.keyBoard = keyBoard;
        this.Quiz = quiz;
        locked = true;
    }

    public Lock(Inventory key) {
        this.key = key;
        locked = true;
    }

    public Lock(){
        locked = false;
    }

    public Quiz getQuiz() {
        return Quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.Quiz = quiz;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    public Boolean Unlock(Inventory key) {
        boolean shouldToggleLock = (this.key == null && Quiz.start(this.keyBoard)) || (this.key != null && this.key.equals(key));
        if (shouldToggleLock) {
            locked = !locked;
        }
        return locked;
    }

}
