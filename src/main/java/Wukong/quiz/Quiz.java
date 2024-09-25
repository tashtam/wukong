package Wukong.quiz;

import Wukong.PrimaryCommands;

import java.util.Scanner;

/**
 * Interface representing a quiz that can be used to unlock locks.
 */

public interface Quiz {

    public PrimaryCommands commands = new PrimaryCommands();

    public boolean start(Scanner scanner);
    public boolean Quiz(Scanner scanner);
}

