package Ships;

public class Hull {

    private int hp;

    public Hull(int hp) {
        this.hp = hp;
    }

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
