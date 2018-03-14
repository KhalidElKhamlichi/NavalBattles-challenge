import BattleStrategy.BattleStrategy;
import BattleStrategy.StandardBattleStrategy;
import BattleStrategy.LocalizedStrategy;
import Ships.Ship;
import Ships.ShipsPack;

public class Battle {

    private ShipsPack firstPack;
    private ShipsPack secondPack;

    private BattleStrategy battleStrategy;

    public Battle() {
        battleStrategy = new StandardBattleStrategy();
    }

    public Battle(int i) {
        battleStrategy = new LocalizedStrategy();
    }

    public boolean isInTheWinningSide(Ship ship) {
        if(firstPack.has(ship))
            return firstPack.isAlive();

        return secondPack.isAlive();
    }

    public Battle side(Ship... ships) {
        firstPack = new ShipsPack(battleStrategy, ships);
        return this;
    }

    public Battle against(Ship... ships) {
        secondPack = new ShipsPack(battleStrategy, ships);
        startBattle();
        return this;
    }

    private void startBattle() {

        setOutnumberingPack();
        while (true) {
            firstPack.attack(secondPack);
            if(!secondPack.isAlive())
                break;
            secondPack.attack(firstPack);
            if(!firstPack.isAlive())
                break;
        }
    }

    private void setOutnumberingPack() {
        if(firstPack.getNumberOfShips() > secondPack.getNumberOfShips()) {
            firstPack.setOutnumbering(true);
        }
        if(firstPack.getNumberOfShips() < secondPack.getNumberOfShips()) {
            secondPack.setOutnumbering(true);
        }
    }
}
