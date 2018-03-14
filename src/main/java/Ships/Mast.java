package Ships;

public class Mast {

    private int hp = 1000;

    public int getHp() {
        return hp;
    }

    public boolean isDestroyed() {
        return hp <= 0;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }
}
