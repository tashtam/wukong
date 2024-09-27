package Wukong;

/**
 * @author Tianfa Zhu
 *
 * This class represents a Monster with a name, health, damage, and an optional treasure.
 */
public class Monster {
    private String name;
    private double health;
    private double damage;
    private Inventory treasure;


    /**
     * Constructs a Monster with the specified attributes.
     *
     * @param name The name of the monster.
     * @param health The health points of the monster.
     * @param damage The damage that the monster can deal.
     * @param treasure The treasure (inventory item) the monster holds, if any.
     */
    public Monster(String name, double health, double damage, Inventory treasure) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.treasure = treasure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Inventory getTreasure() {
        return treasure;
    }

    public void setTreasure(Inventory treasure) {
        this.treasure = treasure;
    }
}
