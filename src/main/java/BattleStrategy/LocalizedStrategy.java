package BattleStrategy;

import Ships.ShipsPack;

public class LocalizedStrategy implements BattleStrategy {

    @Override
    public void attack(ShipsPack shipsPack, int damage) {

        if(shipsPack.hasMast()) {
            shipsPack.takeDamageToMast(damage);
            return;
        }

        if(shipsPack.hasCanons()) {
            shipsPack.takeDamageToCanon(damage);
            return;
        }

        shipsPack.takeDamageToHull(damage);

    }
}
