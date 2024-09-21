package Wukong;

import java.util.ArrayList;

/**
 * The Player class represents a player character in the game, holding attributes such as health, inventory, and weight.
 * It also provides methods to manage the player's inventory and actions.
 */
public class Player {
    private double health;
    private ArrayList<Inventory> inventory;
    private String name;
    private double weight;

    /**
     * Constructs a Player object with the specified health, inventory, and name.
     * Adds a default "stick" item to the player's inventory.
     *
     * @param health The initial health of the player.
     * @param inventory The list of Inventory objects the player starts with.
     * @param name The name of the player.
     */
    public Player(double health, ArrayList<Inventory> inventory, String name) {
        Inventory stick = new Inventory("Your own stick", "stick", 0, 10, 1);
        this.health = health;
        this.inventory = inventory;
        this.name = name;
        this.addInventory(stick);
        this.weight = 4;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public ArrayList<Inventory> getInventory() {
        return inventory;
    }


    public void setInventory(ArrayList<Inventory> inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Adds an item to the player's inventory if they have enough capacity.
     *
     * @param inventory The Inventory object to add.
     * @return True if the item was successfully added, false otherwise.
     */
    public Boolean addInventory(Inventory inventory) {
        if (weight - inventory.getWeight() < 0) {
            return false;
        }
        this.inventory.add(inventory);
        setWeight(getWeight() - inventory.getWeight());
        return true;
    }

    /**
     * Removes an item from the player's inventory, but the "stick" cannot be dropped.
     *
     * @param inventory The Inventory object to remove.
     */
    public void dropInventory(Inventory inventory) {
        ArrayList<Inventory> updatedInventory = new ArrayList<>();

        for (Inventory e : this.inventory) {
            if (!e.getName().equals(inventory.getName())) {
                updatedInventory.add(e);
            }
        }

        if (!inventory.getName().equals("stick")) {
            setWeight(weight + inventory.getWeight());
            setInventory(updatedInventory);
        } else {
            System.out.println("You can't drop your hands ;)");
        }
    }

    /**
     * Checks if the player has a specific item in their inventory by name.
     *
     * @param selectedInventory The name of the item to check.
     * @return The Inventory object if found, or null if not present.
     */
    public Inventory checkInventories(String selectedInventory) {
        for (Inventory inventory : getInventory()) {
            if (inventory.getName().equalsIgnoreCase(selectedInventory)) {
                return inventory;
            }
        }
        return null;
    }

    /**
     * Lists all items in the player's inventory by printing their names to the console.
     */
    public void listInventories() {
        for (Inventory inv : inventory) {
            System.out.println(inv.getName());
        }
    }
}
