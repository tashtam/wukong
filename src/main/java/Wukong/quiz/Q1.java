package Wukong.quiz;

import java.util.Scanner;

/**
 * Represents a specific quiz in the game, where the user has to answer a riddle.
 */
public class Q1 implements Quiz {
    private boolean solved;
    private Runnable onQuit;

    /**
     * Constructs a new Q1 quiz instance with a given action to execute upon quitting.
     *
     * @param onQuit A {@code Runnable} that defines what happens when the user chooses to quit the quiz.
     */
    public Q1(Runnable onQuit) {
        this.onQuit = onQuit;
    }

    /**
     * Starts the quiz by presenting a riddle to the user.
     *
     * @param scanner The {@code Scanner} used to read user input.
     * @return {@code true} if the quiz was solved successfully, {@code false} otherwise.
     */
    @Override
    public boolean start(Scanner scanner) {
        System.out.println(
                "I was born from stone, on a mountain I grew,\n" +
                "With a magic staff, my strength I drew.\n" +
                "I sought immortality, defied the heavens' might,\n" +
                "Who am I, the Great Sage Equal to Heaven, full of power and light?");

        solved = false;
        return Quiz(scanner);
    }

    /**
     * Conducts the quiz by prompting the user for an answer and checking if it is correct.
     *
     * @param keyBoard The {@code Scanner} used to read user input.
     * @return {@code true} if the quiz was answered correctly, {@code false} otherwise.
     */
    @Override
    public boolean Quiz(Scanner keyBoard) {
        while (!solved) {
            String answer = keyBoard.nextLine();
            if (answer.equals("SunWukong")) {
                solved = true;
                System.out.println("Congratulations, you solved the question!");
                break;
            } else if (answer.equals("quit question")) {
                if (onQuit != null) onQuit.run();
                break;
            } else {
                System.out.println("This answer is wrong, please try again!");
            }
        }
        return solved;
    }
}
