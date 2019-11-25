package facade;

public class Main {
    public static void main(String[] args) {

        CarEngineFacade facade = new CarEngineFacade();
        facade.startEngine();
        facade.stopEngine();

    }
}
