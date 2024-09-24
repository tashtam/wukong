package Wukong.quiz;

import java.util.Scanner;

/**
 * Represents a specific quiz in the game, where the user has to answer a riddle.
 */
public class Q3 implements Quiz {
    private boolean solved;
    private Runnable onQuit;

    /**
     * Constructs a new Q3 quiz instance with a given action to execute upon quitting.
     *
     * @param onQuit A {@code Runnable} that defines what happens when the user chooses to quit the quiz.
     */
    public Q3(Runnable onQuit) {
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
        System.out.println("""
                A rock statue of a knight stands before the gate in front of you, strong and mighty even with his eyes closed.
                Suddenly, he opens his eyes, startling you. You reach for your wooden stick, just in case.
                With a booming voice, he tells you that only those who can solve his riddle are granted entry through the gate.
                He watches you with careful eyes, before giving you his riddle of the day:

                'I walk on four feet in the morning.
                I walk on two in the afternoon.
                I walk on three at night.
                Who am I?'

                A. Dog
                B. Human
                C. Horse
                D. Time

                Choose your answer by typing the corresponding letter.
                Or, type 'quit question' to quit the riddle.""");

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
            if (answer.equals("B")) {
                solved = true;
                System.out.println("The statue smiles approvingly, granting you passage.\n" +
                        "'Congratulations, traveler, you solved my riddle!'");
                break;
            } else if (answer.equals("quit question")) {
                if (onQuit != null) onQuit.run();
                break;
            } else {
                System.out.println("""
                        The statue laughs.
                        'HA HA HA! Your answer is wrong, but I'll give you another chance, traveler.
                        What is the correct answer?'""");
            }
        }
        return solved;
    }
}
