import BattleStrategy.*;
import Ships.Ship;
import Ships.Fleet;

public class Battle {

    private Fleet firstPack;
    private Fleet secondPack;

    public Battle() {
        BattleStrategy battleStrategy = new StandardBattleStrategy();
        firstPack = new Fleet(battleStrategy);
        secondPack = new Fleet(battleStrategy);
    }

    public Battle(int localized) {
        BattleStrategy battleStrategy = new LocalizedBattleStrategy();
        firstPack = new Fleet(battleStrategy);
        secondPack = new Fleet(battleStrategy);
    }

    public boolean isInTheWinningSide(Ship ship) {
        if(firstPack.has(ship))
            return firstPack.isAlive();

        return secondPack.isAlive();
    }

    public Battle side(Ship... ships) {
        firstPack.add(ships);
        return this;
    }

    public Battle against(Ship... ships) {
        secondPack.add(ships);
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
