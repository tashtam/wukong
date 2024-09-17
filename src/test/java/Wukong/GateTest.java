package Wukong;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GateTest {

    @Test
    void testGateConstructorWithLock() {
        Area area1 = new Area("Forest", "ForestMap");
        Area area2 = new Area("Cave", "CaveMap");
        Lock lock = new Lock(); 

        Gate gate = new Gate(area1, area2, lock);
        
        assertEquals(area1, gate.getOtherArea(area2), "The other area should be area1");
        assertEquals(area2, gate.getOtherArea(area1), "The other area should be area2");
        assertEquals(lock, gate.getLock(), "The lock should be the same as the one provided");
    }

    @Test
    void testGateConstructorWithoutLock() {
        Area area1 = new Area("Forest", "ForestMap");
        Area area2 = new Area("Cave", "CaveMap");

        Gate gate = new Gate(area1, area2);
        
        assertNull(gate.getLock(), "The lock should be null for this constructor");
        assertEquals(area1, gate.getOtherArea(area2), "The other area should be area1");
        assertEquals(area2, gate.getOtherArea(area1), "The other area should be area2");
    }
}
