package Wukong;


import Wukong.quiz.Q1;
import Wukong.quiz.Q2;
import Wukong.quiz.Q3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {

    private Player player;
    private Area currentArea;
    private Inventory Key1, Key2, Key3;
    private Area lastArea;
    private Scanner keyBoard = new Scanner(System.in);
    private Area HuaguoMount, MountFangcun, WuzhuangTemple, DragonPalace, FlamingMountain, Cave, Heaven, LeiyinTemple,SpiderCave, LionCamelRidge, GreenCloudMountain;
    private Parser parser;

    public Game(String name) {
        parser = new Parser(keyBoard);
        player = new Player(100, new ArrayList<>(), name);
        initAreas();
        initGates();
        currentArea = HuaguoMount;
    }

    private void initAreas() {
        Key1 = new Inventory("Golden Hoop", "key1", 1, 0, 1);
        Key2 = new Inventory("Bajiao Fan", "key2", 1, 0, 1);
        Key3 = new Inventory("Golden Feather", "key3", 1, 0, 1);
        Inventory goldenCudgel = new Inventory("golden cudgel", "golden_cudgel", 1, 50, 1);
        Inventory armor = new Inventory("Cicada Wing armor", "armor", 1, 0, 0.5);
        Inventory feather = new Inventory("Feather", "feather", 1, 60, 0.4);
        Monster BullKing = new Monster("Bull_King", 100, 50, Key2);
        Monster WhiteBoneDemon = new Monster("White Bone Demon", 100, 50, armor);
        Monster GoldenWingedPeng = new Monster("Golden-Winged Great Peng", 200, 90, feather);
        Monster SpiderDemon = new Monster("Spider Demon", 90, 30, Key3);
        HuaguoMount = new Area("Huaguo Mountain", "HuaguoMount");
        Heaven = new Area("Heavenly Palace", Key1, "Heaven");
        Cave = new Area("Mountainside Cave", WhiteBoneDemon, "Cave");
        MountFangcun = new Area("Mount Fangcun of the Scriptures", "MountFangcun");
        WuzhuangTemple = new Area("Taoist WuzhuangTemple", "WuzhuangTemple");
        DragonPalace = new Area("Dragon Palace", goldenCudgel, "DragonPalace");
        FlamingMountain = new Area("Flaming Mountain", BullKing, "FlamingMountain");
        LeiyinTemple = new Area("Leiyin Temple", "LeiyinTemple");
        SpiderCave = new Area("Spider Cave", SpiderDemon,"SpiderCave");
        LionCamelRidge = new Area("Lion Camel Ridge", "LionCamelRidge");
        GreenCloudMountain = new Area("Green Cloud Mountain", GoldenWingedPeng,"GreenCloudMountain");

    }

    private void initGates() {
        Gate[] gates = {
                new Gate(HuaguoMount, DragonPalace, new Lock(new Q1(), keyBoard)),
                new Gate(DragonPalace, Heaven, new Lock()),
                new Gate(HuaguoMount, MountFangcun, new Lock(Key1)),
                new Gate(FlamingMountain, Cave, new Lock(new Q2(), keyBoard)),
                new Gate(MountFangcun, Cave, new Lock(new Q3(), keyBoard)),
                new Gate(MountFangcun, WuzhuangTemple, new Lock(Key2)),
                new Gate(WuzhuangTemple, LeiyinTemple, new Lock()),
                new Gate(WuzhuangTemple, SpiderCave, new Lock()),
                new Gate(SpiderCave, LionCamelRidge, new Lock(Key3)),
                new Gate(LeiyinTemple, GreenCloudMountain, new Lock())
        };

        HuaguoMount.setExits(gates[0], null, gates[2], null);
        DragonPalace.setExits(null, null, gates[0], gates[1]);
        Heaven.setExits(null, gates[1], null, null);
        MountFangcun.setExits(gates[2], gates[4], gates[5], null);
        FlamingMountain.setExits(null, null, null, gates[3]);
        Cave.setExits(null, gates[3], null, gates[4]);
        WuzhuangTemple.setExits(gates[5], gates[7], gates[6], null);
        LeiyinTemple.setExits(gates[6], gates[9], null, null);
        SpiderCave.setExits(null, gates[8], null, gates[7]);
        LionCamelRidge.setExits(null, null, null, gates[8]);
        GreenCloudMountain.setExits(null, null, null, gates[9]);
    }




    public void play() throws IOException {
        Welcome();
        Map.map(currentArea.getMapName());

        processCommands();

        System.out.println("Thank you for playing this game!");
        keyBoard.close();
    }

    private void processCommands() throws IOException {
        Command command = parser.getCommand();
        if (!processCommand(command)) {
            processCommands();
        }
    }
    
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

    private void GoCommand(Command command) {
        try {
            System.out.println(command);
            goArea(command);
        } catch (NullPointerException e) {
            System.out.println("There's no Gate!");
        }
    }



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

    private Inventory findInventoryByName(ArrayList<Inventory> Inventories, String name) {
        for (Inventory Inventory : Inventories) {
            if (Inventory.getName().equalsIgnoreCase(name)) {
                return Inventory;
            }
        }
        return null;
    }

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


    private void Guide() {
        System.out.println("tips\n\nYour command words are:\n" + parser.showCommands());
    }



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

        Lock lock = Gate.getLock();

        if (lock.isLocked()) {
            handleLockedGate(lock, nextArea);
        } else {
            switchAreas(nextArea);
        }
    }

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

