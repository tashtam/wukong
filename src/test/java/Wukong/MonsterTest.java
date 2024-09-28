package Wukong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * The MonsterTest class is designed to test the functionality of the Monster class,
 * ensuring that it correctly manages its attributes such as name, health, damage, and treasure.
 * This test class includes test cases to verify the constructor and various setter methods
 * for the Monster class, as well as the handling of null treasures.
 * 
 * @author Tianfa Zhu
 */
public class MonsterTest {

    private Monster dragon;
    private Inventory treasure;

    @Before
    public void setUp() {
        treasure = new Inventory("Gold Coin", "gold", 10, 1, 5);
        dragon = new Monster("Dragon", 100.0, 25.0, treasure);
    }

    @Test
    public void testConstructor() {
        assertEquals("Dragon", dragon.getName());
        assertEquals(100.0, dragon.getHealth(), 0.01);
        assertEquals(25.0, dragon.getDamage(), 0.01);
        assertEquals(treasure, dragon.getTreasure());
    }

    @Test
    public void testSetName() {
        dragon.setName("Evil Dragon");
        assertEquals("Evil Dragon", dragon.getName());
    }

    @Test
    public void testSetHealth() {
        dragon.setHealth(80.0);
        assertEquals(80.0, dragon.getHealth(), 0.01);
    }

    @Test
    public void testSetDamage() {
        dragon.setDamage(30.0);
        assertEquals(30.0, dragon.getDamage(), 0.01);
    }

    @Test
    public void testSetTreasure() {
        Inventory newTreasure = new Inventory("Diamond", "gem", 50, 1, 10);
        dragon.setTreasure(newTreasure);
        assertEquals(newTreasure, dragon.getTreasure());
    }

    @Test
    public void testTreasureNull() {
        Monster ghost = new Monster("Ghost", 50.0, 10.0, null);
        assertNull(ghost.getTreasure());
    }
}
