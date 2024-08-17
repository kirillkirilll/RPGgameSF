import java.util.Random;

public class Character {
    Random random = new Random(100);

    private String name;
    private int healthPoint;
    private int gold;
    private int dexterity;
    private int xp;
    private int strength;

    public Character(String name, int healthPoint, int gold, int dexterity, int xp, int strength) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.gold = gold;
        this.dexterity = dexterity;
        this.xp = xp;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoint;
    }

    public int getGold() {
        return gold;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getXp() {
        return xp;
    }

    public int getStrength() {
        return strength;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int attack() {
        if (dexterity * 3 > Math.random() * 100) return strength;
        else return 0;
    }

    @Override
    public String toString() {
        return "Health " + name + " equals " + healthPoint;
    }
}
