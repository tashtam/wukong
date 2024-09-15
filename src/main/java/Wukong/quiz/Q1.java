package Wukong.quiz;

import java.util.Scanner;

public class Q1 implements Quiz {
    private boolean solved;
    private String question;

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

    @Override
    public boolean Quiz(Scanner keyBoard) {
        while (!solved) {
            String answer = keyBoard.nextLine();
            if (answer.equals("SunWukong")) {
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