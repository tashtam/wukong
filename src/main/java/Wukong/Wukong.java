package Wukong;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for starting and running the Wukong game.
 * @author Tianfa Zhu
 * @author Tashia Tamara
 */
public class Wukong {

    /**
     * The entry point of the Wukong game application.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If an input or output error occurs during game initialization.
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""

                Welcome to Wukong, a text-based adventure game!

                In this game, your goal as an adventurer is to get to the Leiyin Temple.
                You've heard a rumor that someone has left a big treasure box full of gold inside the temple!
                However, your journey to get there will be riddled with lots of challenges.
                Will you successfully reach the temple? Or will you succumb to the many obstacles along the way?

                There's only one way to find out!

                Before we start, please enter your name.

                Player name:""");
        String playerName = scanner.nextLine();

        Game Wukonggame = new Game(playerName);
        Wukonggame.play();
    }
}
