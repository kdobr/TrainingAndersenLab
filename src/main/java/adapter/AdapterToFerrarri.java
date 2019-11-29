package adapter;

import adapter.Cars.MovableFast;
import adapter.Cars.MovableSlow;

public class AdapterToFerrarri implements MovableSlow {

    private MovableFast fastCar;

    public AdapterToFerrarri(MovableFast fastCar) {
        this.fastCar = fastCar;
    }

    @Override
    public String tryToStartMe() {
        return fastCar.getHighSpeed();
    }
}
