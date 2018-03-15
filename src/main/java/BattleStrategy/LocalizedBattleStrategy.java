package BattleStrategy;

import Ships.Fleet;

public class LocalizedBattleStrategy implements BattleStrategy {

    @Override
    public void attack(Fleet fleet, int damage) {

        if(fleet.hasMast()) {
            fleet.takeDamageToMast(damage);
            return;
        }

        if(fleet.hasCanons()) {
            fleet.takeDamageToCanon(damage);
            return;
        }

        fleet.takeDamageToHull(damage);

    }
}
