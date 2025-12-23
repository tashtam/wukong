package Wukong;

/**
 * @author Tianfa Zhu
 * @author Tashia Tamara
 *
 * This class represents an inventory item in the game.
 * Each item has information such as its name, weight, damage, and defense properties.
 */
public class Inventory {
    private String info;
    private String name;
    private int weight;
    private int damage;
    private double Defense;

    public Inventory(String info, String name, int weight, int damage, double Defense) {
        this.info = info;
        this.name = name;
        this.damage = damage;
        this.Defense = Defense;
        this.weight = weight;
        
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getDefense() {
        return Defense;
    }

    public void setDefense(double Defense) {
        this.Defense = Defense;
    }

    @Override
    public String toString() {
        return name;
    }
}
