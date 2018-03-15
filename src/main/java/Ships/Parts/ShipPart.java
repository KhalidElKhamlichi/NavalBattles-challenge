package Ships.Parts;

public abstract class ShipPart {

    protected int hp;

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public boolean isDestroyed() {
        return hp <= 0;
    }

    public int getHp() {
        return hp;
    }
}
