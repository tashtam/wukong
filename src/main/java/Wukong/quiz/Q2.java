package Wukong.quiz;

import java.util.Scanner;

public class Q2 implements Quiz {
    private boolean solved;
    private String question;

    @Override
    public boolean start(Scanner scanner) {
        System.out.println("With nine bends in a river, I guard day and night,\n" +
                "My waters are deadly, no living thing in sight.\n" +
                "Only a monk with purity, can cross my waves and plight,\n" +
                "What am I, the challenge on the monk’s holy flight?");

        solved = false;
        return Quiz(scanner);
    }

    @Override
    public boolean Quiz(Scanner keyBoard) {
        while (!solved) {
            String answer = keyBoard.nextLine();
            if (answer.equals("ShaWujing")) {
                solved = true;
                System.out.println("Congratulations, you solved the question!");
                break;
            } else if (answer.equals("quit question")) {
                break;
            } else {
                System.out.println("This answer is wrong, please try again!");
            }
        }
        return solved;
    }
}
