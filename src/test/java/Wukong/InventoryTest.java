package Wukong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link Inventory} class.
 * These tests validate the functionality of the Inventory class,
 * including getting and setting attributes such as damage, name, weight, defense, and info.
 * This ensures that inventory items in the game, like weapons and shields,
 * behave as expected when interacting with their properties.
 *
 * The tests cover methods for retrieving and updating values related to damage, defense,
 * weight, and other item-specific details.
 *
 * @author Yang Cao
 */

public class InventoryTest {

    private Inventory sword;
    private Inventory shield;

    @Before
    public void setUp() {
        // Initialize inventory objects for testing
        sword = new Inventory("A sharp sword", "Sword", 5, 20, 0.0);
        shield = new Inventory("A sturdy shield", "Shield", 10, 0, 15.5);
    }

    @Test
    public void testGetDamage() {
        // Test getDamage() method
        assertEquals(20, sword.getDamage());
        assertEquals(0, shield.getDamage());
    }

    @Test
    public void testSetDamage() {
        // Test setDamage() method
        sword.setDamage(30);
        assertEquals(30, sword.getDamage());
    }

    @Test
    public void testGetInfo() {
        // Test getInfo() method
        assertEquals("A sharp sword", sword.getInfo());
        assertEquals("A sturdy shield", shield.getInfo());
    }

    @Test
    public void testSetInfo() {
        // Test setInfo() method
        sword.setInfo("An ancient sword");
        assertEquals("An ancient sword", sword.getInfo());
    }

    @Test
    public void testGetName() {
        // Test getName() method
        assertEquals("Sword", sword.getName());
        assertEquals("Shield", shield.getName());
    }

    @Test
    public void testSetName() {
        // Test setName() method
        sword.setName("Excalibur");
        assertEquals("Excalibur", sword.getName());
    }

    @Test
    public void testGetWeight() {
        // Test getWeight() method
        assertEquals(5, sword.getWeight());
        assertEquals(10, shield.getWeight());
    }

    @Test
    public void testSetWeight() {
        // Test setWeight() method
        sword.setWeight(6);
        assertEquals(6, sword.getWeight());
    }

    @Test
    public void testGetDefense() {
        // Test getDefense() method
        assertEquals(0.0, sword.getDefense(), 0.01);
        assertEquals(15.5, shield.getDefense(), 0.01);
    }

    @Test
    public void testSetDefense() {
        // Test setDefense() method
        shield.setDefense(20.0);
        assertEquals(20.0, shield.getDefense(), 0.01);
    }
}
