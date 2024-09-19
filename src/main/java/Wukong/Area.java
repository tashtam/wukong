package Wukong;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Area class represents a location in the game with information about its surroundings,
 * possible exits, contained items, and any monsters present.
 */
public class Area {

    private String info;
    private Monster monster;
    private HashMap<String, Gate> exits;
    private String mapName;
    private ArrayList<Inventory> inventories;

    private boolean hasTimeChallenge;
    private TimeChallenge timeChallenge;
    
    /**
     * Constructs an Area with specified information, inventory, monster, and map name.
     *
     * @param info        Description of the area.
     * @param inventory   An inventory item present in the area, can be null.
     * @param monster     A monster present in the area, can be null.
     * @param mapName     Name of the map this area belongs to.
     */
    public Area(String info, Inventory inventory, Monster monster, String mapName) {
        this.info = info;
        this.monster = monster;
        this.exits = new HashMap<>();
        this.inventories = new ArrayList<>();
        this.mapName = mapName;
        if (inventory != null) {
            this.inventories.add(inventory);
        }
    }

    public Area(String info, Inventory inventory, String mapName) {
        this(info, inventory, null, mapName);
    }

    public Area(String info, Monster monster, String mapName) {
        this(info, null, monster, mapName);
    }

    public Area(String info, String mapName) {
        this(info, null, null, mapName);
    }

    //Modify the constructor to initialize the time challenge(Ziying)
    public Area(String info, Monster monster, String mapName, boolean hasTimeChallenge) {
        this.mapName = mapName;
        this.info = info;
        this.hasTimeChallenge = hasTimeChallenge;
        if (hasTimeChallenge) {
            timeChallenge = new TimeChallenge(60000); // 60 seconds time challenge
        }
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }


    /**
     * Sets the exits for the area with specified gates.
     *
     * @param gates The gates representing the exits.
     */
    public void setExits(Gate... gates) {
        String[] directions = {"north", "east", "south", "west"};
        for (int i = 0; i < gates.length; i++) {
            if (i < directions.length) {
                exits.put(directions[i], gates[i]);
            }
        }
    }

    public String shortInfo() {
        return info;
    }

    public String longInfo() {
        return "You are in " + info + ".\n" + exitString();
    }

    private String exitString() {
        return "Exits: " + String.join(" ", exits.keySet());
    }

    public Area nextArea(String direction) {
        return exits.get(direction).getOtherArea(this);
    }

    public void addInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    public void removeInventory(Inventory inventory) {
        inventories.removeIf(e -> e.getName().equals(inventory.getName()));
    }

    public boolean MonsterExists() {
        return monster != null;
    }

    public void removeMonster() {
        this.monster = null;
    }

    public boolean InventoryExists() {
        return !inventories.isEmpty();
    }

    public Monster getMonster() {
        return monster;
    }

    public ArrayList<Inventory> getInventories() {
        return inventories;
    }

    public HashMap<String, Gate> getExits() {
        return exits;
    }


    //Add methods to start the time challenge and check its status(Ziying)
    public void startTimeChallenge() {
        if (hasTimeChallenge && timeChallenge != null) {
            timeChallenge.start();
        }
    }
    public boolean isTimeChallengeOver() {
        return hasTimeChallenge && timeChallenge != null && timeChallenge.isTimeOver();
    }
    public boolean hasTimeChallenge() {
        return hasTimeChallenge;
    }
}
