package Wukong.quiz;

import java.util.Scanner;

public class Q3 implements Quiz {
    private boolean solved;
    private String question;

    @Override
    public boolean start(Scanner scanner) {
        System.out.println("I travel with a monk, on a quest from the West,\n" +
                "Though I am the youngest, my strength is the best.\n" +
                "With my brothers and master, through trials we’ve pressed,\n" +
                "Who am I, the one who is always neatly dressed?");

        solved = false;
        return Quiz(scanner);
    }

    @Override
    public boolean Quiz(Scanner keyBoard) {
        while (!solved) {
            String answer = keyBoard.nextLine();
            if (answer.equals("ZhuBajie")) {
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
