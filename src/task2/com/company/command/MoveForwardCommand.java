package task2.com.company.command;
import task2.com.company.Tractor;

public class MoveForwardCommand implements Command {

    Tractor tractor;

    public MoveForwardCommand(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void execute() {
tractor.moveForward();
    }
}
