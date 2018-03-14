package Ships;

public class Canon {

    public static final int damage = 200;

    private int hp = 100;

    public int getHp() {
        return hp;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public boolean isDestroyed() {
        return hp <= 0;
    }
}
