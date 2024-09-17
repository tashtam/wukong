package Wukong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private Inventory stick;
    private Inventory sword;

    @BeforeEach
    void setUp() {
        // Setup inventory items
        stick = new Inventory("Your own stick", "stick", 0, 10, 1);
        sword = new Inventory("Shiny Sword", "sword", 15, 5, 2);

        // Setup player with initial health, inventory, and name
        ArrayList<Inventory> initialInventory = new ArrayList<>();
        initialInventory.add(stick);
        player = new Player(100, initialInventory, "Hero");
    }

    @Test
    void testAddInventory() {
        // Add a new inventory item to the player
        boolean result = player.addInventory(sword);

        // Verify that adding was successful and item is in inventory
        assertFalse(result);
        assertFalse(player.getInventory().contains(sword));
        assertEquals(2, player.getInventory().size());
    }

    @Test
    void testAddInventoryOverweight() {
        // Set player's weight limit lower to test overweight condition
        player.setWeight(0);

        // Try adding an item that is too heavy
        boolean result = player.addInventory(sword);

        // Verify that adding was unsuccessful
        assertFalse(result);
        assertFalse(player.getInventory().contains(sword));
    }

    @Test
    void testDropInventory() {
        // Drop an item from inventory
        player.dropInventory(sword);

        // Verify that the item is removed and weight is adjusted
        assertFalse(player.getInventory().contains(sword));
        assertEquals(19, player.getWeight(), 0.01); // Weight should be reset after dropping
    }

    @Test
    void testDropStick() {
        // Try to drop the stick item
        player.dropInventory(stick);

        // Verify that the stick cannot be dropped
        assertTrue(player.getInventory().contains(stick));
        assertEquals(4, player.getWeight(), 0.01); // Weight should not change
    }

    @Test
    void testCheckInventories() {
        // Check for an item in the inventory
        Inventory found = player.checkInventories("stick");

        // Verify that the correct item is found
        assertNotNull(found);
        assertEquals("stick", found.getName());
    }
}
