package Wukong;


import Wukong.quiz.Q1;
import Wukong.quiz.Q2;
import Wukong.quiz.Q3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main game engine for the text-based adventure game "Wukong".
 * It manages the game state, player actions, and transitions between different areas.
 *
 * @author 
 */
public class Game {

    private Player player;
    private Area currentArea;
    private Inventory Key1, Key2;
    private Area lastArea;
    private Scanner keyBoard = new Scanner(System.in);
    private Area HuaguoMount, MountFangcun, WuzhuangTemple, DragonPalace, FlamingMountain, Cave, Heaven, LeiyinTemple;
    private Parser parser;

    //TimeChallenge
    private long challengeStartTime;
    private long challengeDuration = 10000; // 10 seconds for the time challenge
    private boolean isTimeChallengeActive = false;

    public Game(String name) {
        parser = new Parser(keyBoard);
        player = new Player(100, new ArrayList<>(), name);
        initAreas();
        initGates();
        currentArea = HuaguoMount;
    }

    /**
     * Initializes all areas in the game.
     */

    private void initAreas() {
        Key1 = new Inventory("Golden Hoop", "key1", 1, 0, 1);
        Key2 = new Inventory("Bajiao Fan", "key2", 1, 0, 1);
        Inventory goldenCudgel = new Inventory("golden cudgel", "golden_cudgel", 1, 50, 1);
        Inventory armor = new Inventory("Cicada Wing armor", "armor", 1, 0, 0.5);
        Monster BullKing = new Monster("Bull_King", 100, 50, Key2);
        Monster WhiteBoneDemon = new Monster("White Bone Demon", 100, 50, armor);

        HuaguoMount = new Area("Huaguo Mountain", "HuaguoMount");
        Heaven = new Area("Heavenly Palace", Key1, "Heaven");
        Cave = new Area("Mountainside Cave", WhiteBoneDemon, "Cave");
        MountFangcun = new Area("Mount Fangcun of the Scriptures", "MountFangcun");
        WuzhuangTemple = new Area("Taoist WuzhuangTemple", "WuzhuangTemple");
        DragonPalace = new Area("Dragon Palace", goldenCudgel, "DragonPalace");
        FlamingMountain = new Area("Flaming Mountain", BullKing, "FlamingMountain");
        LeiyinTemple = new Area("Leiyin Temple", "LeiyinTemple");
    }
    
    /**
     * Initializes all gates connecting different areas in the game.
     */
    private void initGates() {
        Gate[] gates = {
                new Gate(HuaguoMount, DragonPalace, new Lock(new Q1(this::returnToMap), keyBoard)),
                new Gate(DragonPalace, Heaven, new Lock()),
                new Gate(HuaguoMount, MountFangcun, new Lock(Key1)),
                new Gate(FlamingMountain, Cave, new Lock(new Q2(this::returnToMap), keyBoard)),
                new Gate(MountFangcun, Cave, new Lock(new Q3(this::returnToMap), keyBoard)),
                new Gate(MountFangcun, WuzhuangTemple, new Lock(Key2)),
                new Gate(WuzhuangTemple, LeiyinTemple, new Lock())
        };

        HuaguoMount.setExits(gates[0], null, gates[2], null);
        DragonPalace.setExits(null, null, gates[0], gates[1]);
        Heaven.setExits(null, gates[1], null, null);
        MountFangcun.setExits(gates[2], gates[4], gates[5], null);
        FlamingMountain.setExits(null, null, null, gates[3]);
        Cave.setExits(null, gates[3], null, gates[4]);
        WuzhuangTemple.setExits(gates[5], null, gates[6], null);
        LeiyinTemple.setExits(gates[6], null, null, null);
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

        System.out.println("Thank you for playing this game!");
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
                .append("Welcome to Wukong!\n")
                .append("Wukong is a text-based adventure game.\n")
                .append("You can type 'guide' if you require assistance.\n")
                .append("Your command words are 'go quit collect guide inventory drop map'")
                .append("\n")
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
                if (currentArea.shortInfo().equals("Leiyin Temple")) {
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
            System.out.println("There's no Gate!");
        }
    }


    /**
     * Handles the "collect" command, allowing the player to collect items from the current area.
     */
    private void CollectCommand() {
        if (!currentArea.InventoryExists()) {
            System.out.println("There are no items to collect");
            return;
        }

        ArrayList<Inventory> Inventories = currentArea.getInventories();
        if (Inventories.size() > 1) {
            System.out.println("There are more than 1 item, which one do you collect?");
            Inventories.forEach(Inventory -> System.out.println(Inventory.getName()));
            String InventoryChosen = keyBoard.nextLine();
            Inventory Inventory = findInventoryByName(Inventories, InventoryChosen);

            while (Inventory == null) {
                System.out.println("Choose one again");
                Inventories.forEach(System.out::println);
                InventoryChosen = keyBoard.nextLine();
                Inventory = findInventoryByName(Inventories, InventoryChosen);
            }

            if (player.addInventory(Inventory)) {
                currentArea.removeInventory(Inventory);
                System.out.println("item added successfully");
            } else {
                System.out.println("Item cannot be added due to weight restrictions: " + Inventory.getWeight());
                System.out.println("Your weight currently: " + player.getWeight());
            }
        } else {
            Inventory Inventory = Inventories.get(0);
            if (player.addInventory(Inventory)) {
                System.out.println("Inventory: " + Inventory.getName() + " added successfully");
                currentArea.removeInventory(Inventory);
            } else {
                System.out.println("Could not add item due to weight: " + Inventory.getWeight());
                System.out.println("Your weight currently: " + player.getWeight());
            }
        }
    }


    /**
     * Finds an inventory item by its name.
     *
     * @param Inventories the list of inventories to search
     * @param name the name of the inventory item
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
                System.out.println("Item not found. Please check if it was spelled correctly");
            } else {
                player.dropInventory(selectedInventory);
                currentArea.addInventory(selectedInventory);
                System.out.println("Inventory dropped successfully");
            }
        } else {
            System.out.println("What do you want to drop specifically?");
            player.listInventories();
        }
    }

    /**
     * Displays the guide for the game.
     */
    private void Guide() {
        System.out.println("tips\n\nYour command words are:\n" + parser.showCommands());
    }


    /**
     * Handles movement between areas.
     *
     * @param command the command to process
     */
    private void goArea(Command command) {
        if (!command.hasAdditionalCommand()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getAdditionalCommand();
        Area nextArea = currentArea.nextArea(direction);
        Gate Gate = currentArea.getExits().get(direction);

        if (nextArea == null) {
            System.out.println("No Gate here!");
            return;
        }

        // Check if the time challenge is active and if the player failed to leave the "Cave"(Time Challenge)
        if (isTimeChallengeActive && currentArea.shortInfo().equals("Mountainside Cave")) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - challengeStartTime) >= challengeDuration) {
                System.out.println("Time's up! You failed the Mountainside Cave challenge. Returning to the Mountainside Cave...");
                switchAreas(Cave); // Force the player back to the "Cave"
                return;
            }
        }

        Lock lock = Gate.getLock();

        if (lock.isLocked()) {
            handleLockedGate(lock, nextArea);
        } else {
            switchAreas(nextArea);
            // If the player successfully leaves the "Cave," end the time challenge(Time Challenge)
            if (!currentArea.shortInfo().equals("Mountainside Cave")) {
                isTimeChallengeActive = false;
            }
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
            System.out.println("You need a key to pass");
            System.out.println("Here is your Inventory: ");
            player.listInventories();

            String selectedInventory = keyBoard.nextLine();
            Inventory keySelected = player.checkInventories(selectedInventory);

            if (keySelected != null && !lock.Unlock(keySelected)) {
                switchAreas(nextArea);
            } else {
                System.out.println("Wrong key");
            }
        }
    }


    /**
     * Returns to the map view from a quiz-based lock. If there is an
     * error or no map is available, an appropriate message is displayed.
     */
    private void returnToMap() {
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
    private void switchAreas(Area nextArea) {
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

        // Start the time challenge if the player enters the "Cave"(Time Challenge)
        if (currentArea.shortInfo().equals("Cave")) {
            isTimeChallengeActive = true;
            challengeStartTime = System.currentTimeMillis();
            System.out.println("You have entered the Cave. You must leave within 60 seconds or start over!");
        }


        if (currentArea.MonsterExists()) {
            System.out.println("you've encountered " + currentArea.getMonster().getName() + ". You have to Combat.");
       
            if (new Combat(currentArea.getMonster(), player, keyBoard).Combat()) {
          
                currentArea.addInventory(currentArea.getMonster().getTreasure());
                System.out.println("\nGather your treasure from the Monster with the 'collect' command.");
                currentArea.removeMonster();  
            } else {
                System.exit(0);
            }
        }
    }



}

