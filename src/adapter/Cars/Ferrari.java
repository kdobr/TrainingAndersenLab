package adapter.Cars;

import adapter.Cars.MovableFast;

public class Ferrari implements MovableFast {

    @Override
    public String getHighSpeed() {
        return "we run very fast";
    }
}
