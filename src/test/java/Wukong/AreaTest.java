package Wukong;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Unit tests for the {@link Area} class.
 * These tests ensure the functionality of the {@link Area} class,
 * including adding monsters, setting exits, and retrieving map names.
 *
 * @author Yang Cao
 */

public class AreaTest {

    private Area areaWithMonster;
    private Area areaWithoutMonster;
    private Inventory sword;
    private Monster monster;
    private Area emptyArea;
    private Gate northGate;
    private Gate eastGate;

    @Before
    public void setUp() {
        sword = new Inventory("A sharp sword", "Sword", 5, 20, 0.0);
        monster = new Monster("Golden-Winged Great Peng", 100, 30, null);

        // Initialize areas and gates for testing
        areaWithMonster = new Area("Heavenly Palace", sword, monster, "Heaven");
        areaWithoutMonster = new Area("Heavenly Palace", sword, "Heaven");
        emptyArea = new Area("Cave", "CaveMap");

        northGate = new Gate(emptyArea, areaWithMonster, null);  // Example gate without lock
        eastGate = new Gate(areaWithMonster, areaWithoutMonster);  // Example gate without lock
    }

    @Test
    public void testGetMapName() {
        // Test map name retrieval
        assertEquals("Heaven", areaWithMonster.getMapName());
        assertEquals("CaveMap", emptyArea.getMapName());
    }

    @Test
    public void testSetMapName() {
        // Test setting the map name
        emptyArea.setMapName("NewCaveMap");
        assertEquals("NewCaveMap", emptyArea.getMapName());
    }

    @Test
    public void testSetMonster() {
        // Test setting a monster in the area
        emptyArea.setMonster(monster);
        assertEquals(monster, emptyArea.getMonster());
    }

    @Test
    public void testGetMonster() {
        // Test monster retrieval
        assertEquals(monster, areaWithMonster.getMonster());
        assertNull(areaWithoutMonster.getMonster());  // Area without monster should return null
    }

    @Test
    public void testSetExits() {
        // Set exits for the area
        areaWithMonster.setExits(northGate, eastGate);

        HashMap<String, Gate> exits = areaWithMonster.getExits();
        assertEquals(northGate, exits.get("north"));
        assertEquals(eastGate, exits.get("east"));
    }

    @Test
    public void testAddInventory() {
        // Test adding an inventory item to the area
        Inventory shield = new Inventory("A sturdy shield", "Shield", 10, 0, 5.0);
        emptyArea.addInventory(shield);

        ArrayList<Inventory> inventories = emptyArea.getInventories();
        assertEquals(1, inventories.size());
        assertEquals(shield, inventories.get(0));
    }

    @Test
    public void testGetInfo() {
        // Test retrieving area description
        assertEquals("Heavenly Palace", areaWithMonster.getName());
        assertEquals("Cave", emptyArea.getName());
    }
}
