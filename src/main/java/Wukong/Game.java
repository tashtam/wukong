package Wukong;


import Wukong.quiz.Q1;
import Wukong.quiz.Q2;
import Wukong.quiz.Q3;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Tianfa Zhu
 * @author Tashia Tamara
 *
 * Represents the main game engine for the text-based adventure game "Wukong," inspired by the classic game Zork.
 * It manages the game state, player actions, and transitions between different areas.
 */
public class Game {

    private Player player;
    private Area currentArea;
    private Inventory moonKey, sunKey, lionCamelKey, diamondKey;
    private Area lastArea;
    private Scanner keyBoard = new Scanner(System.in);
    private Area HuaguoMount, MountFangcun, WuzhuangTemple, DragonPalace, FlamingMountain, Cave, Heaven, LeiyinTemple, SpiderCave, LionCamelRidge, GreenCloudMountain;
    private Parser parser;

    private List<Monster> monsters = new ArrayList<>();
    private List<Inventory> inventories = new ArrayList<>();


    public Game(String name) {
        parser = new Parser(keyBoard);
        player = new Player(100, new ArrayList<>(), name);
        initAreas();
        initGates();
        currentArea = HuaguoMount;
    }

    private void loadFromJson(String monsterFilePath, String inventoryFilePath) {
        Gson gson = new GsonBuilder().create();
        loadJson(monsterFilePath, "monsters", Monster.class, monsters, gson);
        loadJson(inventoryFilePath, "inventories", Inventory.class, inventories, gson);
    }

    private <T> void loadJson(String filePath, String arrayName, Class<T> clazz, List<T> list, Gson gson) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            gson.fromJson(reader, JsonObject.class)
                    .getAsJsonArray(arrayName)
                    .forEach(element -> list.add(gson.fromJson(element, clazz)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes all areas in the game.
     */

    private void initAreas() {
        loadFromJson("src/main/Data/monster.json", "src/main/Data/inventory.json");

        Monster BullKing = monsters.get(0);
        Monster WhiteBoneDemon = monsters.get(1);
        Monster GoldenWingedPeng = monsters.get(2);
        Monster SpiderDemon = monsters.get(3);

        moonKey = inventories.get(0);
        Inventory goldenCudgel = inventories.get(1);
        sunKey = inventories.get(2);
        diamondKey = inventories.get(3);
        lionCamelKey = inventories.get(4);
        
        HuaguoMount = new Area("""
                Huaguo Mountain.
                The mountain is lush with trees and vegetation.
                In the distance, you hear the distant shower of the waterfall""", "HuaguoMount");

        Heaven = new Area("""
                Heavenly Palace.
                The palace towers before you, its grand white pillars standing strong.
                You walk around the palace, your each step echoing loudly in the quiet space""", moonKey, "Heaven");

        Cave = new Area("""
                Mountainside Cave.
                The cave seems dark and eerie.
                Something about it makes you feel uneasy""", WhiteBoneDemon, "Cave");

        MountFangcun = new Area("""
                Mount Fangcun of the Scriptures.
                Impossibly tall bookshelves have been carved into the mountain.
                The shelves are filled with endless rows of books and scriptures,
                that look like they've been sitting in the shelves for a very long time""", "MountFangcun");

        WuzhuangTemple = new Area("Taoist Wuzhuang Temple.\n" +
                "As you enter, the temple's gates, you smell the sweet scent of burning incense." +
                "It makes you feel at peace", "WuzhuangTemple");

        DragonPalace = new Area("""
                Dragon Palace.
                A huge statue of a dragon sits on top of the roof of the palace,
                watching you with wide eyes, like it's guarding the place""", goldenCudgel, "DragonPalace");

        FlamingMountain = new Area("Flaming Mountain.\n" +
                "Smoke billows out from the peak of the mountain." +
                "You feel hot, and you can feel beads of sweat form on your forehead.",sunKey, BullKing, "FlamingMountain");

        SpiderCave = new Area("Spider Cave.\n" +
                "The name of this cave alone sends shivers down your spine." +
                "You really, really hate spiders", lionCamelKey,SpiderDemon, "SpiderCave");

        LionCamelRidge = new Area("""
                Lion Camel Ridge.
                The shape of the ridge resembles a lion when being viewed from the north,
                and resembles a camel when viewed from the south""", "LionCamelRidge");

        GreenCloudMountain = new Area("""
                Green Cloud Mountain.
                You've hiked for so long, and you're exhausted.
                You look around and find that you've reached a height above the clouds""",diamondKey, GoldenWingedPeng, "GreenCloudMountain");

        LeiyinTemple = new Area("Leiyin Temple. Just like the rumors said, the treasure box is sitting there, waiting for you. You have completed the game", "LeiyinTemple");

    }

    /**
     * Initializes all gates connecting different areas in the game.
     */
    private void initGates() {
        Gate[] gates = {
                new Gate(HuaguoMount, DragonPalace, new Lock(new Q1(this::returnToMap), keyBoard)),
                new Gate(DragonPalace, Heaven, new Lock()),
                new Gate(HuaguoMount, MountFangcun, new Lock(moonKey)),
                new Gate(FlamingMountain, Cave, new Lock(new Q2(this::returnToMap), keyBoard)),
                new Gate(MountFangcun, Cave, new Lock(new Q3(this::returnToMap), keyBoard)),
                new Gate(MountFangcun, WuzhuangTemple, new Lock(sunKey)),
                new Gate(WuzhuangTemple, LeiyinTemple, new Lock(diamondKey)),
                new Gate(WuzhuangTemple, SpiderCave, new Lock()),
                new Gate(SpiderCave, LionCamelRidge, new Lock(lionCamelKey)),
                new Gate(LionCamelRidge, GreenCloudMountain, new Lock())
        };

        HuaguoMount.setExits(gates[0], null, gates[2], null);
        DragonPalace.setExits(null, null, gates[0], gates[1]);
        Heaven.setExits(null, gates[1], null, null);
        MountFangcun.setExits(gates[2], gates[4], gates[5], null);
        FlamingMountain.setExits(null, null, null, gates[3]);
        Cave.setExits(null, gates[3], null, gates[4]);
        WuzhuangTemple.setExits(gates[5], gates[7], gates[6], null);
        LeiyinTemple.setExits(gates[6], null, null, null);
        SpiderCave.setExits(null, gates[8], null, gates[7]);
        LionCamelRidge.setExits(null, gates[9], null, gates[8]);
        GreenCloudMountain.setExits(null, null, null, gates[9]);
    }


    /**
     * Starts the game and handles the game loop.
     *
     * @throws IOException if an I/O error occurs
     */
    public void play() throws IOException {
        Welcome();
        Map.map(currentArea.getMapName());

        processCommands();
        System.out.println("Thank you for playing.");
        keyBoard.close();
    }


    /**
     * Processes user commands in a loop until the game is exited.
     *
     * @throws IOException if an I/O error occurs
     */

    private void processCommands() throws IOException {
        Command command = parser.getCommand();
        if (!processCommand(command)) {
            processCommands();
        }
    }

    /**
     * Displays the welcome message when the game starts.
     */
    private void Welcome() {
        StringBuilder welcomeMessage = new StringBuilder()
                .append("\n")
                .append("Welcome to Wukong!\n\n")
                .append("Your command words are 'go quit collect guide inventory drop map inspect'.\n\n")
                .append("Type 'guide' if you require assistance.\n")
                .append("Type 'collect' every time you enter a new location to search for items.\n")
                .append("Type 'inspect' if you want more information about an item in your inventory.")
                .append("\n\n")
                .append("Good luck!\n\n")
                .append(currentArea.longInfo());

        System.out.print(welcomeMessage.toString());
    }

    /**
     * Processes a user command.
     *
     * @param command the command to process
     * @return true if the game should be exited, false otherwise
     * @throws IOException if an I/O error occurs
     */
    private boolean processCommand(Command command) throws IOException {
        if (command.isUnknown()) {
            System.out.println("Sorry, I don't understand that...");
            return false;
        }

        String primaryCommand = command.getPrimaryCommand();
        switch (primaryCommand) {
            case "guide":
                Guide();
                break;

            case "go":
                GoCommand(command);
                if (currentArea.shortInfo().equals("Leiyin Temple. Just like the rumors said, the treasure box is sitting there, waiting for you. You have completed the game")) {
                    return true;
                }
                break;

            case "quit":
                if (!command.hasAdditionalCommand()) {
                    return true;
                }
                System.out.println("Quit?");
                break;

            case "collect":
                CollectCommand();
                break;

            case "inventory":
                player.listInventories();
                break;

            case "drop":
                DropCommand(command);
                break;

            case "inspect":
                InspectCommand(command);
                break;

            case "map":
                Map.map(currentArea.getMapName());
                break;

            default:
                System.out.println("Unknown command.");
                break;
        }
        return false;
    }


    /**
     * Handles the "go" command, allowing the player to move to a different area.
     *
     * @param command the command to process
     */
    private void GoCommand(Command command) {
        try {
            System.out.println(command);
            goArea(command);
        } catch (NullPointerException e) {
            System.out.println("There is no gate there!");
        }
    }


    /**
     * Handles the "collect" command, allowing the player to collect items from the current area.
     */
    private void CollectCommand() {
        if (!currentArea.InventoryExists()) {
            System.out.println("There are no items to collect.");
            return;
        }

        ArrayList<Inventory> Inventories = currentArea.getInventories();
        if (Inventories.size() > 1) {
            System.out.println("Multiple items found. Which one would you like to collect?");
            Inventories.forEach(Inventory -> System.out.println(Inventory.getName()));
            String InventoryChosen = keyBoard.nextLine();
            Inventory Inventory = findInventoryByName(Inventories, InventoryChosen);

            while (Inventory == null) {
                System.out.println("Choose an item.");
                Inventories.forEach(System.out::println);
                InventoryChosen = keyBoard.nextLine();
                Inventory = findInventoryByName(Inventories, InventoryChosen);
            }

            if (player.addInventory(Inventory)) {
                currentArea.removeInventory(Inventory);
                System.out.println("Item added successfully!");
            } else {
                System.out.println("Item cannot be added due to weight restrictions: " + Inventory.getWeight());
                System.out.println("Remaining weight capacity: " + player.getWeight());
            }
        } else {
            Inventory Inventory = Inventories.get(0);
            if (player.addInventory(Inventory)) {
                System.out.println("Inventory: " + Inventory.getName() + " added successfully!");
                currentArea.removeInventory(Inventory);
            } else {
                System.out.println("Item cannot be added due to weight restrictions: " + Inventory.getWeight());
                System.out.println("Remaining weight capacity: " + player.getWeight());
            }
        }
    }


    /**
     * Finds an inventory item by its name.
     *
     * @param Inventories the list of inventories to search
     * @param name        the name of the inventory item
     * @return the inventory item if found, null otherwise
     */
    private Inventory findInventoryByName(ArrayList<Inventory> Inventories, String name) {
        for (Inventory Inventory : Inventories) {
            if (Inventory.getName().equalsIgnoreCase(name)) {
                return Inventory;
            }
        }
        return null;
    }


    /**
     * Handles the "drop" command, allowing the player to drop an item from their inventory.
     *
     * @param command the command to process
     */

    private void DropCommand(Command command) {
        if (command.hasAdditionalCommand()) {
            Inventory selectedInventory = player.checkInventories(command.getAdditionalCommand());

            if (selectedInventory == null) {
                System.out.println("Item not found. Please check if it was spelled correctly.");
            } else {
                String selectedInventoryName = selectedInventory.getName(); 
                player.dropInventory(selectedInventory);
                currentArea.addInventory(selectedInventory);

                if (!"Wooden Stick".equalsIgnoreCase(selectedInventoryName)) {
                    System.out.println("Item dropped successfully!");
                }
            }
        } else {
            System.out.println("Which item would you like to drop? (To drop an item, type 'drop' followed by the item name)");
            player.listInventories();
        }
    }


    /**
     * Handles the "inspect" command, allowing the player to find out more information / explanation about their selected inventory item.
     *
     * @param command the command to process
     */
    private void InspectCommand(Command command) {
        if (command.hasAdditionalCommand()) {
            Inventory selectedInventory = player.checkInventories(command.getAdditionalCommand());
            if (selectedInventory == null) {
                System.out.println("Item not found. Please check if it was spelled correctly.");
            } else {
                System.out.println(player.inspectInventoryItem(selectedInventory));
            }
        } else {
            System.out.println("Which item would you like to inspect? (To inspect an item, type 'inspect' followed by the item name)");
            player.listInventories();
        }
    }

    /**
     * Displays the guide for the game.
     */
    private void Guide() {
        System.out.println("Your command words are:\n" + parser.showCommands());
    }


    /**
     * Handles movement between areas.
     *
     * @param command the command to process
     */
    private void goArea(Command command) {
        if (!command.hasAdditionalCommand()) {
            System.out.println("Where would you like to go?");
            return;
        }

        String direction = command.getAdditionalCommand();
        Area nextArea = currentArea.nextArea(direction);
        Gate Gate = currentArea.getExits().get(direction);

        if (nextArea == null) {
            System.out.println("There is no gate there!");
            return;
        }

        Lock lock = Gate.getLock();

        if (lock.isLocked()) {
            handleLockedGate(lock, nextArea);
        } else {
            switchAreas(nextArea);
        }
    }


    /**
     * Handles the logic for interacting with a locked gate, including
     * processing the quiz or key required to unlock the gate.
     *
     * @param lock     The lock object associated with the gate.
     * @param nextArea The area to switch to if the gate is successfully unlocked.
     */
    private void handleLockedGate(Lock lock, Area nextArea) {
        if (lock.getQuiz() != null) {
            if (!lock.Unlock(null)) {
                switchAreas(nextArea);
            }
        } else {
            System.out.println("You need a key to pass.");

            while (true) {
                System.out.println("Inventory:");
                player.listInventories();
                System.out.println("Type 'exit' to leave this area.");

                String selectedInventory = keyBoard.nextLine();

                if (selectedInventory.equalsIgnoreCase("exit")) {
                    System.out.println("You have chosen to leave the area.");
                    returnToMap();
                    return;
                }

                Inventory keySelected = player.checkInventories(selectedInventory);

                if (keySelected != null && !lock.Unlock(keySelected)) {
                    switchAreas(nextArea);
                    break;
                } else {
                    System.out.println("Wrong key! Please try again.");
                }
            }
        }
    }
    

    /**
     * Returns to the map view from a quiz-based lock. If there is an
     * error or no map is available, an appropriate message is displayed.
     */
    void returnToMap() {
        try {
            if (currentArea.getMapName() != null) {
                Map.map(currentArea.getMapName());
            } else {
                System.out.println("No map available.");
            }
        } catch (IOException e) {
            System.out.println("Error reading map: " + e.getMessage());
        }
    }


    /**
     * Switches to a new area and updates the current area. Displays
     * information about the new area and handles potential encounters
     * with monsters. If a monster is encountered and defeated, updates
     * the area with the monster's treasure.
     *
     * @param nextArea The area to switch to.
     */
    void switchAreas(Area nextArea) {
        lastArea = currentArea;
        currentArea = nextArea;
        System.out.println(currentArea.longInfo());

        try {
            if (currentArea.getMapName() != null) {
                Map.map(currentArea.getMapName());
            } else {
                System.out.println("No map available.");
            }
        } catch (IOException e) {
            System.out.println("Error reading map: " + e.getMessage());
        }


        if (currentArea.MonsterExists()) {
            System.out.println("You've encountered " + currentArea.getMonster().getName() +
                    ".\nYou have to defeat this monster to proceed." +
                    "\nYou have 10 seconds to select an item, otherwise 'Wooden Stick' will automatically be selected.");

            if (new Combat(currentArea.getMonster(), player, keyBoard).Combat()) {

                currentArea.addInventory(currentArea.getMonster().getTreasure());
                System.out.println("\nGather your treasure from the monster using the 'collect' command.");
                currentArea.removeMonster();
            } else {
                System.exit(0);
            }
        }
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public Player getPlayer() {
        return player;
    }
    


}

