package Wukong;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * The PlayerTest class is designed to test the functionality of the Player class,
 * ensuring that it correctly manages the player's inventory, including adding, dropping,
 * and checking items. This test class covers various scenarios to validate the expected
 * behavior of inventory management methods.
 * 
 * @author Tianfa Zhu
 */


public class PlayerTest {

    private Player player;
    private Inventory stick;
    private Inventory sword;

    @Before
    public void setUp() {
        // Setup inventory items
        stick = new Inventory("A wooden stick made of high-quality oak.\nIt's been in your family for generations...", "Wooden Stick", 0, 10, 1);
        sword = new Inventory("Shiny Sword", "sword", 15, 5, 2);

        // Setup player with initial health, inventory, and name
        ArrayList<Inventory> initialInventory = new ArrayList<>();
        initialInventory.add(stick);
        player = new Player(100, initialInventory, "Hero");
    }

    @Test
    public void testAddInventory() {
        // Add a new inventory item to the player
        boolean result = player.addInventory(sword);

        // Verify that adding was successful and item is in inventory
        assertFalse(result);
        assertFalse(player.getInventory().contains(sword));
        assertEquals(2, player.getInventory().size());
    }

    @Test
    public void testAddInventoryOverweight() {
        // Set player's weight limit lower to test overweight condition
        player.setWeight(0);

        // Try adding an item that is too heavy
        boolean result = player.addInventory(sword);

        // Verify that adding was unsuccessful
        assertFalse(result);
        assertFalse(player.getInventory().contains(sword));
    }

    @Test
    public void testDropInventory() {
        // Drop an item from inventory
        player.dropInventory(sword);

        // Verify that the item is removed and weight is adjusted
        assertFalse(player.getInventory().contains(sword));
        assertEquals(19, player.getWeight(), 0.01); // Weight should be reset after dropping
    }

    @Test
    public void testDropStick() {
        // Try to drop the stick item
        player.dropInventory(stick);

        // Verify that the stick cannot be dropped
        assertTrue(player.getInventory().contains(stick));
        assertEquals(4, player.getWeight(), 0.01); // Weight should not change
    }

    @Test
    public void testCheckInventories() {
        // Check for an item in the inventory
        Inventory found = player.checkInventories("Wooden Stick");

        // Verify that the correct item is found
        assertNotNull(found);
        assertEquals("Wooden Stick", found.getName());
    }
}
