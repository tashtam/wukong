package Wukong;


public class Monster {
    private String name;
    private double health;
    private double damage;
    private Inventory treasure;

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
