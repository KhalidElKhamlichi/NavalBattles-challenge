package BattleStrategy;

import Ships.ShipsPack;

public class StandardBattleStrategy implements BattleStrategy {

    @Override
    public void attack(ShipsPack shipsPack, int damage) {
        shipsPack.takeOverallDamage(damage);

    }
}
