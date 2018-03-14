import Ships.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {
    private List<Ship> ships;

    public Race(Ship... ships) {
        this.ships = new ArrayList<>(Arrays.asList(ships));
    }

    public Ship winner() {
        float max = Float.MIN_VALUE;
        Ship fastestShip = null;
        for (Ship ship : ships) {
            float speed = ship.getSpeed();
            if(speed > max) {
               max = speed;
               fastestShip = ship;
            }
        }

        return fastestShip;
    }
}
