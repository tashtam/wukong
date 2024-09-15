package Wukong;

import java.util.ArrayList;


public class Player {
    private double health;
    private ArrayList<Inventory> inventory;
    private String name;
    private double weight;


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
    public Boolean addInventory(Inventory Inventory) {
        if (weight - Inventory.getWeight() < 0) {
            return false;
        }
        inventory.add(Inventory);
        setWeight(getWeight() - Inventory.getWeight());
        return true;
    }

    public void dropInventory(Inventory Inventory) {
        ArrayList<Inventory> updatedInventory = new ArrayList<>();
        
        for (Inventory e : inventory) {
            if (!e.getName().equals(Inventory.getName())) {
                updatedInventory.add(e);
            }
        }

        // Further explanation required
        if (!Inventory.getName().equals("stick")) {
            setWeight(weight + Inventory.getWeight()); 
            setInventory(updatedInventory); 
        } else {
            System.out.println("You can't drop your hands ;)");
        }
    }


    public Inventory checkInventories(String selectedInventory) {
        for (Inventory Inventory : getInventory()) {
            if (Inventory.getName().equalsIgnoreCase(selectedInventory)) {
                return Inventory;
            }
        }
        return null;
    }

    public void listInventories() {
        for (Inventory inv : inventory) {
            System.out.println(inv.getName());
        }
    }

}

