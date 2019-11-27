package task2.com.company.command;

import task2.com.company.Tractor;

public class TurnClockwiseCommand implements Command {
    Tractor tractor;

    public TurnClockwiseCommand(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void execute() {
        tractor.turnClockwise();
    }
}
