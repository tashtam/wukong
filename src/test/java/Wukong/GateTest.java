package Wukong;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * The GateTest class is designed to test the functionality of the Gate class,
 * specifically focusing on the constructors and their handling of areas and locks.
 * This test class includes test cases to ensure that the Gate class behaves 
 * correctly when initialized with and without a lock.
 * 
 * @author Tianfa Zhu
 */
public class GateTest {

    @Test
    public void testGateConstructorWithLock() {
        Area area1 = new Area("Forest", "ForestMap");
        Area area2 = new Area("Cave", "CaveMap");
        Lock lock = new Lock();

        Gate gate = new Gate(area1, area2, lock);

        assertEquals("The other area should be area1", area1, gate.getOtherArea(area2));
        assertEquals("The other area should be area2", area2, gate.getOtherArea(area1));
        assertEquals("The lock should be the same as the one provided", lock, gate.getLock());
    }

    @Test
    public void testGateConstructorWithoutLock() {
        Area area1 = new Area("Forest", "ForestMap");
        Area area2 = new Area("Cave", "CaveMap");

        Gate gate = new Gate(area1, area2);

        assertNull("The lock should be null for this constructor", gate.getLock());
        assertEquals("The other area should be area1", area1, gate.getOtherArea(area2));
        assertEquals("The other area should be area2", area2, gate.getOtherArea(area1));
    }
}
