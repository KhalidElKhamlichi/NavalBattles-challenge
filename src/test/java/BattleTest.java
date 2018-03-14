import Ships.Ship;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BattleTest {
    private static final int LOCALIZED_DAMAGES = 0;


    // each ship has :
    // hull has 1 hit point per ton of displacement
    // 100 hit point per canon
    // 1000 hit point per mast

    // each canon does 200hp damage

    // this is a global fight, damages are not localized, nor impact dynamically ship statistics
    // fight last until one side is sunk
    // damages happens simultaneously

    @Test
    public void fightWithSameFirePower() {
        Ship a = new Ship(7500, 1, 16);
        Ship b = new Ship(12000, 1, 16);

        Battle battle = new Battle().side(a).against(b);
        assertThat(battle.isInTheWinningSide(b)).isTrue();
    }

    @Test
    public void fightWithDifferentFirePower() {
        Ships.Ship a = new Ships.Ship(2000, 1, 15); // 3k damage, 4500 hp (2000 + 1000 + 1500), can take 3 hits [should win]
        // 3k damage, 2000 hp (just displacement), can take 1 hits
        Ships.Ship b = new Ships.Ship(3100, 1, 10); // 2k damage, 5100 hp (3100 + 1000 + 1000), can take 2 hits
        // 2k damage, 3000 hp (just displacement), can take 2 hits [wins]

        Battle battle = new Battle().side(a).against(b);
        assertThat(battle.isInTheWinningSide(a)).isTrue();
    }


    // when the battle is not even in terms of number of ship
    // each ship of the outnumbering team gains an 15 % bonus per additional ship to it's damages.
    // each ship always target the same opponent until it's sunk (once sunk it can not participate to the fight anymore, nor count for the presence & calculation of the bonus)
    @Test
    public void packfight() {
        Ships.Ship a = new Ships.Ship(65000, 3, 64);
        Ships.Ship b = new Ships.Ship(23000, 1, 24);
        Ships.Ship c = new Ships.Ship(23000, 1, 24);
        Ships.Ship d = new Ships.Ship(23000, 1, 24);

        Battle battle = new Battle().side(a).against(b, c, d);
        assertThat(battle.isInTheWinningSide(b)).isTrue();
        assertThat(battle.isInTheWinningSide(c)).isTrue();
        assertThat(battle.isInTheWinningSide(d)).isTrue();
    }


    // this one comes for free as control group for next one :D
    @Test
    public void packfightLightBoat() {
        Ships.Ship a = new Ships.Ship(65000, 3, 64);
        Ships.Ship b = new Ships.Ship(15000, 1, 24);
        Ships.Ship c = new Ships.Ship(15000, 1, 24);
        Ships.Ship d = new Ships.Ship(15000, 1, 24);

        Battle battle = new Battle().side(a).against(b, c, d);
        assertThat(battle.isInTheWinningSide(a)).isTrue();
    }

    // damages are now localized and a applied in this order :
    // first mast are destroyed, then cannon, then the hull
    // destroyed part of the ship changes it's characteristics :
    // a destroyed mast reduce overall ship speed
    // a destroyed canon can not fire anymore thus it's ship does less damages
    // a full destroyed hull means the ship has sunk
    @Test
    public void packfightLightBoatLocalized() {
        Ships.Ship a = new Ships.Ship(65000, 3, 64);
        Ships.Ship b = new Ships.Ship(23000, 1, 24);
        Ships.Ship c = new Ships.Ship(23000, 1, 24);
        Ships.Ship d = new Ships.Ship(23000, 1, 24);

        Battle battle = new Battle(LOCALIZED_DAMAGES).side(a).against(b, c, d);
        assertThat(battle.isInTheWinningSide(b)).isTrue();
        assertThat(battle.isInTheWinningSide(c)).isTrue();
        assertThat(battle.isInTheWinningSide(d)).isTrue();
    }

}