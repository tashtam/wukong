package Wukong;

import java.io.IOException;
import java.util.Scanner;

public class Wukong {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game Wukong\nPlayer name: ");
        String playerName = scanner.nextLine();
 
        Game Wukonggame = new Game(playerName);
        Wukonggame.play();
    }
}
