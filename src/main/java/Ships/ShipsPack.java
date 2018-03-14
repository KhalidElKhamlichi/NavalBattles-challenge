package Ships;

import BattleStrategy.BattleStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShipsPack {

    private List<Ship> ships;
    private BattleStrategy battleStrategy;

    private boolean isOutnumbering = false;

    private final int bonusDamagePercentage = 15;

    public ShipsPack(BattleStrategy battleStrategy, Ship... ships) {
        this.ships = new ArrayList<>(Arrays.asList(ships));
        this.battleStrategy = battleStrategy;
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

    public void attack(ShipsPack shipsPack) {
        if(isOutnumbering)
            applyBonusDamageToShips();

        for (Ship ship : ships) {
            int damage = ship.getDamage();
            battleStrategy.attack(shipsPack, damage);
        }
    }

    private void applyBonusDamageToShips() {
        int numberOfAdditionalShips = ships.size() - 1;

        for(Ship ship : ships) {
            ship.applyBonusDamage(numberOfAdditionalShips * bonusDamagePercentage);
        }
    }

    public void takeOverallDamage(int damage) {
        Ship shipToTakeDmg = ships.get(0);
        shipToTakeDmg.takeOverallDamage(damage);

        if(!shipToTakeDmg.isAlive())
            ships.remove(shipToTakeDmg);
        System.out.println(ships+" - damage taken: "+damage+" - outnumbering :"+isOutnumbering);
    }

    public int getNumberOfShips() {
        return ships.size();
    }

    public void setOutnumbering(boolean outnumbering) {
        isOutnumbering = outnumbering;
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
}
