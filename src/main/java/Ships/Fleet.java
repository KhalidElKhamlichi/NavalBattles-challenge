package Ships;

import BattleStrategy.BattleStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fleet {

    private List<Ship> ships;
    private BattleStrategy battleStrategy;

    private boolean isOutnumbering = false;

    private final int bonusDamagePercentage = 15;

    public Fleet(BattleStrategy battleStrategy) {
        this.battleStrategy = battleStrategy;
    }

    public void add(Ship... ships) {
        this.ships = new ArrayList<>(Arrays.asList(ships));
    }

    public void attack(Fleet fleet) {

        for (Ship ship : ships) {
            int outputDamage = ship.getOutputDamage();
            outputDamage = applyBonusDamage(outputDamage);
            battleStrategy.attack(fleet, outputDamage);
        }
    }

    private int applyBonusDamage(int outputDamage) {
        if(isOutnumbering)
            outputDamage += getBonusDamage(outputDamage);
        return outputDamage;
    }

    private float getBonusDamage(int damage) {
        int numberOfAdditionalShips = ships.size() - 1;
        return numberOfAdditionalShips * (damage * ((float)bonusDamagePercentage / 100));
    }

    public boolean has(Ship b) {
        return ships.contains(b);
    }

    public boolean isAlive() {
        for (Ship ship : ships) {
            if (ship.isAlive())
                return true;
        }
        return false;
    }

    public void takeOverallDamage(int damage) {
        Ship shipToTakeDmg = ships.get(0);
        shipToTakeDmg.takeOverallDamage(damage);

        if(!shipToTakeDmg.isAlive())
            ships.remove(shipToTakeDmg);
    }

    public boolean hasCanons() {
        return ships.get(0).hasCanons();
    }

    public void takeDamageToCanon(int damage) {
        ships.get(0).takeDamageToCanon(damage);
    }

    public boolean hasMast() {
        return ships.get(0).hasMast();
    }

    public void takeDamageToMast(int damage) {
        ships.get(0).takeDamageToMast(damage);
    }

    public void takeDamageToHull(int damage) {
        ships.get(0).takeDamageToHull(damage);
    }

    public int getNumberOfShips() {
        return ships.size();
    }

    public void setOutnumbering(boolean outnumbering) {
        isOutnumbering = outnumbering;
    }
}
