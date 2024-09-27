package Wukong;

/**
 * @author Tianfa Zhu
 *
 * This class represents a gate connecting two areas in the game.
 * A gate may have an optional lock.
 */
public class Gate {
    private Area firstArea;
    private Area secondArea;
    private Lock lock;

    /**
     * Creates a gate between two areas with an optional lock.
     *
     * @param firstArea  The first area connected by this gate.
     * @param secondArea The second area connected by this gate.
     * @param lock       The lock for the gate, or null if there is no lock.
     */
    public Gate(Area firstArea, Area secondArea, Lock lock) {
        this.firstArea = firstArea;
        this.secondArea = secondArea;
        this.lock = lock;
    }

    /**
     * Creates a gate between two areas without a lock.
     *
     * @param firstArea  The first area connected by this gate.
     * @param secondArea The second area connected by this gate.
     */
    public Gate(Area firstArea, Area secondArea) {
        this(firstArea, secondArea, null);
    }

    /**
     * Returns the area on the opposite side of the gate from the given area.
     *
     * @param area The current area.
     * @return The other area connected by the gate.
     */
    public Area getOtherArea(Area area) {
        return (area == firstArea) ? secondArea : firstArea;
    }

    /**
     * Returns the lock for this gate, or null if there is no lock.
     *
     * @return The lock for this gate.
     */
    public Lock getLock() {
        return lock;
    }
}
