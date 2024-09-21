package Wukong;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for starting and running the Wukong game.
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
        System.out.println("Welcome to Wukong, a text-based adventure game!\nPlayer name: ");
        String playerName = scanner.nextLine();

        Game Wukonggame = new Game(playerName);
        Wukonggame.play();
    }
}
