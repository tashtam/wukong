package Wukong;


import java.util.List;
import java.util.Scanner;

public class Combat {
    private Player player;
    private Monster Monster;
    private Scanner keyBoard;

    public Combat(Monster Monster, Player player, Scanner keyBoard) {
        this.player = player;
        this.Monster = Monster;
        this.keyBoard = keyBoard;
    }

    public boolean Combat() {
        // Initialize player health to maximum if necessary
        initializePlayerHealth();

        while (playerIsAlive() && monsterIsAlive()) {
            Inventory selected = selectInventory();

            // Apply damage to the Monster
            playerAttacks(selected);

            // If the Monster is still alive, it attacks back
            if (monsterIsAlive()) {
                monsterAttacks();
            }

            // Check the result of the Combat
            if (!monsterIsAlive()) {
                System.out.println("YOU WIN");
                return true;
            } else if (!playerIsAlive()) {
                System.out.println("YOU LOSE");
                return false;
            }

            // Display remaining Monster health
            displayMonsterHealth();
        }

        return false; // Default to losing if loop s without win
    }

    private void initializePlayerHealth() {
        player.setHealth(Math.min(player.getHealth(), 100));
    }

    private boolean playerIsAlive() {
        return player.getHealth() > 0;
    }

    private boolean monsterIsAlive() {
        return Monster.getHealth() > 0;
    }

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

    private void playerAttacks(Inventory selected) {
        System.out.println("You caused damage: " + selected.getDamage());
        Monster.setHealth(Monster.getHealth() - selected.getDamage());
    }

    private void monsterAttacks() {
        System.out.println("The monster is attacking you..."); 
        double damageMultiplier = 0.3 + Math.random() * 0.5;
        double damage = Monster.getDamage() * damageMultiplier * highestDefense().getDefense();
        player.setHealth(player.getHealth() - damage);
        System.out.println("Your current HP is: " + player.getHealth());
    }

    private void displayMonsterHealth() {
        System.out.println("Monster has " + Monster.getHealth() + " HP");
    }


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

        System.out.println(Defense.getName());
        return Defense;
    }

}
