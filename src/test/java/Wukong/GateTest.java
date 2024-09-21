package Wukong;

import org.junit.Test;
import static org.junit.Assert.*;

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
