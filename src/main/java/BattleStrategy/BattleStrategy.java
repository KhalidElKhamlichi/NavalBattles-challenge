package BattleStrategy;

import Ships.ShipsPack;

public interface BattleStrategy {

    void attack(ShipsPack shipsPack, int damage);
}
