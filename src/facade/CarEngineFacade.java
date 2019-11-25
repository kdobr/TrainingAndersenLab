package facade;

import facade.CarSystem.CoolingController;
import facade.CarSystem.FuelInjector;
import facade.CarSystem.Starter;

public class CarEngineFacade {


    private FuelInjector fuelInjector = new FuelInjector();
    private Starter starter = new Starter();
    private CoolingController coolingController = new CoolingController();

    public void startEngine() {
        fuelInjector.on();
        starter.start();
        coolingController.run();
    }

    public void stopEngine() {
        starter.off();
        fuelInjector.off();
        coolingController.stop();
    }
}
