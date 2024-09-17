package Wukong;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the combat mechanics between a player and a monster.
 * The combat involves selecting items from the player's inventory,
 * attacking the monster, and receiving counter-attacks from the monster.
 * 
 * @author
 */

public class Combat {
    private Player player;
    private Monster Monster;
    private Scanner keyBoard;

    /**
     * Constructs a new Combat instance.
     *
     * @param Monster The monster to be fought in combat.
     * @param player  The player engaging in combat.
     * @param keyBoard The Scanner object for user input.
     */
    public Combat(Monster Monster, Player player, Scanner keyBoard) {
        this.player = player;
        this.Monster = Monster;
        this.keyBoard = keyBoard;
    }

    /**
     * Executes the combat sequence between the player and the monster.
     * The player and monster take turns attacking each other until
     * either the player or the monster is defeated.
     *
     * @return true if the player wins the combat, false otherwise.
     */
    public boolean Combat() {
        // Initialize player health to maximum if necessary
        initializePlayerHealth();

        while (playerIsAlive() && monsterIsAlive()) {
            Inventory selected = selectInventory();

            // Apply damage to the monster
            playerAttacks(selected);

            // Pause and wait for user input to continue
            waitForEnter();

            // If the monster is still alive, it attacks back
            if (monsterIsAlive()) {
                monsterAttacks();

                // Pause and wait for user input to continue
                waitForEnter();
            }

            // Check the result of the combat
            if (!monsterIsAlive()) {
                System.out.println("YOU WIN");
                return true;
            } else if (!playerIsAlive()) {
                System.out.println("YOU LOSE");
                return false;
            }
        }

        return false; // Default to losing if loop exits without a win
    }

    /**
     * Initializes the player's health to a maximum value of 100 if
     * it is currently set higher than this value.
     */
    private void initializePlayerHealth() {
        player.setHealth(Math.min(player.getHealth(), 100));
    }

    /**
     * Checks if the player is still alive based on their current health.
     *
     * @return true if the player's health is greater than 0, false otherwise.
     */
    private boolean playerIsAlive() {
        return player.getHealth() > 0;
    }

    /**
     * Checks if the monster is still alive based on its current health.
     *
     * @return true if the monster's health is greater than 0, false otherwise.
     */
    private boolean monsterIsAlive() {
        return Monster.getHealth() > 0;
    }

    /**
     * Prompts the player to select an item from their inventory and
     * ensures a valid item is selected.
     *
     * @return The selected Inventory item.
     */
    private Inventory selectInventory() {
        Inventory selected = null;
        while (selected == null) {
            System.out.println("Select your item: ");
            player.listInventories();
            String selectedInventory = keyBoard.nextLine();
            selected = player.checkInventories(selectedInventory);

            if (selected == null) {
                System.out.println("Item not found. Please choose another one.");
            }
        }
        return selected;
    }

    /**
     * Executes the player's attack on the monster using the selected item.
     *
     * @param selected The item selected by the player for the attack.
     */
    private void playerAttacks(Inventory selected) {
        System.out.println("You caused damage " + selected.getDamage() + " to the monster");
        Monster.setHealth(Monster.getHealth() - selected.getDamage());
        System.out.println("Monster has " + Monster.getHealth() + " HP");
    }

    /**
     * Executes the monster's attack on the player. The damage is based
     * on the monster's attack power and a random multiplier, adjusted by
     * the player's highest defense item.
     */
    private void monsterAttacks() {
        System.out.println("The monster is attacking you...");
        double damageMultiplier = 0.3 + Math.random() * 0.5;
        double damage = Monster.getDamage() * damageMultiplier * highestDefense().getDefense();
        player.setHealth(player.getHealth() - damage);
        System.out.println("Your current HP is: " + player.getHealth());
    }

    /**
     * Pauses the combat and waits for the user to press Enter to continue.
     */
    private void waitForEnter() {
        System.out.println("Press Enter to continue...");
        keyBoard.nextLine(); // Wait for the user to press Enter
    }

    /**
     * Finds the inventory item with the highest defense value.
     *
     * @return The inventory item with the highest defense, or null if the inventory is empty.
     */
    public Inventory highestDefense() {
        List<Inventory> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            return null;
        }

        Inventory Defense = inventory.get(0);

        for (Inventory Inventory : inventory) {
            if (Inventory.getDefense() > Defense.getDefense()) {
                Defense = Inventory;
            }
        }

        return Defense;
    }
}
