package Wukong.quiz;

import java.util.Scanner;

/**
 * Represents a specific quiz in the game, where the user has to answer a riddle.
 */
public class Q2 implements Quiz {
    private boolean solved;
    private Runnable onQuit;

    /**
     * Constructs a new Q2 quiz instance with a given action to execute upon quitting.
     *
     * @param onQuit A {@code Runnable} that defines what happens when the user chooses to quit the quiz.
     */
    public Q2(Runnable onQuit) {
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
        System.out.println("With nine bends in a river, I guard day and night,\n" +
                "My waters are deadly, no living thing in sight.\n" +
                "Only a monk with purity, can cross my waves and plight,\n" +
                "What am I, the challenge on the monk’s holy flight?");

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
            if (answer.equals("ShaWujing")) {
                solved = true;
                System.out.println("You solved the question");
                break;
            } else if (answer.equals("quit question")) {
                if (onQuit != null) onQuit.run();
                break;
            } else {
                System.out.println("This answer is wrong, try again");
            }
        }
        return solved;
    }
}
