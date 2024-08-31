package Wukong;


public class Gate {
    private Area firstArea;
    private Area secondArea;
    private Lock lock;

    public Gate(Area firstArea, Area secondArea, Lock lock) {
        this.firstArea = firstArea;
        this.secondArea = secondArea;
        this.lock = lock;
    }

    public Gate(Area firstArea, Area secondArea) {
        this(firstArea, secondArea, null);
    }

    public Area getOtherArea(Area area) {
        return (area == firstArea) ? secondArea : firstArea;
    }
    
    public Lock getLock() {
        return lock;
    }


}

