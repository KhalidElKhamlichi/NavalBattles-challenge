package Ships;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private List<Canon> canons;
    private List<Mast> masts;
    private Hull hull;
    private int bonusDamagePercentage = 0;
    private int totalHp;
    protected float speed;
    
    public Ship(int tonsOfDisplacement, int numberOfMasts, int numberOfCanons) {
        
        hull = new Hull(tonsOfDisplacement);
        totalHp = hull.getHp();
        
        masts = new ArrayList<>();
        for (int i=0; i<numberOfMasts; i++) {
            Mast mast = new Mast();
            masts.add(mast);
            totalHp += mast.getHp();
        }
        
        canons = new ArrayList<>();
        for (int i=0; i<numberOfCanons; i++) {
            Canon canon = new Canon();
            canons.add(canon);
            totalHp += canon.getHp();
        }
        calculateSpeed(tonsOfDisplacement, numberOfMasts);
    }

    public Ship(int tonsOfDisplacement, int numberOfMasts) {
        this(tonsOfDisplacement, numberOfMasts, 0);
        calculateSpeed(tonsOfDisplacement, numberOfMasts);
    }

    private void calculateSpeed(int tonsOfDisplacement, int numberOfMasts) {
        speed = (float) 1 / (tonsOfDisplacement / numberOfMasts);
        float speedPenalty = (canons.size() * 0.15f);
        speed -= speed * speedPenalty;
    }

    public boolean isAlive() {
        return totalHp > 0 && !hull.isDestroyed();
    }

    public void applyBonusDamage(int bonusDamagePercentage) {
        this.bonusDamagePercentage = bonusDamagePercentage;
    }

    private int calculateCanonsDamage() {
        return canons.size() * Canon.damage;
    }

    public int getDamage() {
        int canonsDamage = calculateCanonsDamage();
        int bonusDamage = (int) (canonsDamage * ((float)bonusDamagePercentage / 100));
        return (canonsDamage + bonusDamage);
    }

    public void takeOverallDamage(int damage) {
        totalHp -= damage;
    }

    public void takeDamageToCanon(int damage) {

        Canon canon = canons.get(0);
        canon.takeDamage(damage);

        if(canon.isDestroyed())
            canons.remove(canon);
    }

    public boolean hasCanons() {
        return canons.size() > 0;
    }

    public boolean hasMast() {
        return masts.size() > 0;
    }

    public void takeDamageToMast(int damage) {
        Mast mast = masts.get(0);
        mast.takeDamage(damage);

        if(mast.isDestroyed())
            masts.remove(mast);
    }

    public void takeDamageToHull(int damage) {
        hull.takeDamage(damage);

    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return ""+this.totalHp;
    }
}
