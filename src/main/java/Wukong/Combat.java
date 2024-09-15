package Wukong;


import java.util.*;

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
                System.out.println("YOU WON!");
                return true;
            } else if (!playerIsAlive()) {
                System.out.println("YOU LOST!");
                return false;
            }

            // Display remaining Monster health
            displayMonsterHealth();
        }

        return false; // Default to losing if loop s without win
    }

    private void initializePlayerHealth() {
        player.setHealth((int) Math.min(player.getHealth(), 100));
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
        ArrayList<String> playerAttackText = new ArrayList<>(Arrays.asList(
                "You lunge forward and hit the monster!",
                "You deliver a roundhouse kick to the monster's head!",
                "You threw a strong uppercut punch, causing the monster to stumble backwards!"
        ));

        // Pick randomly from the player attack text options available
        Random random = new Random();
        int randomIndex = random.nextInt(playerAttackText.size());
        String randomPlayerAttackText = playerAttackText.get(randomIndex);

        // Display the randomly chosen player attack text along with the damage dealt by the player
        System.out.println(randomPlayerAttackText + " Damage: " + selected.getDamage());

        Monster.setHealth((int) Math.round(Monster.getHealth() - selected.getDamage()));
    }

    private void monsterAttacks() {
        ArrayList<String> monsterAttackText = new ArrayList<>(Arrays.asList(
                "The monster roars and hits you in the stomach!",
                "The monster bares its teeth before charging at you, causing you to fall over!",
                "The monster glares menacingly and spins around to kick you!"
        ));

        // Pick randomly from the monster attack text options available
        Random random = new Random();
        int randomIndex = random.nextInt(monsterAttackText.size());
        String randomMonsterAttackText = monsterAttackText.get(randomIndex);

        double damageMultiplier = 0.3 + Math.random() * 0.5;
        double damageByMonster = Monster.getDamage() * damageMultiplier * highestDefense().getDefense();

        // Display the randomly picked monster attack text along with the damage dealt by the monster
        System.out.println(randomMonsterAttackText + " Damage: " + (int) damageByMonster);

        player.setHealth((int) Math.round(player.getHealth() - damageByMonster));
        System.out.println("Your current HP is: " + (int) player.getHealth());
    }

    private void displayMonsterHealth() {
        System.out.println("The monster's current HP is: " + (int) Monster.getHealth() + " HP");
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
