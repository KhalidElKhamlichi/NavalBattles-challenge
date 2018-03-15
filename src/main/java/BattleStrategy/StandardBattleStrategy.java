package BattleStrategy;

import Ships.Fleet;

public class StandardBattleStrategy implements BattleStrategy {

    @Override
    public void attack(Fleet fleet, int damage) {
        fleet.takeOverallDamage(damage);

    }
}
