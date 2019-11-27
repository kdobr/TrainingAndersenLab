package task2.com.company;

import task2.com.company.command.Command;

public class RemoteControl {
    Command[] commands;

    public RemoteControl() {
        commands = new Command[5];;
    }

    public void setCommand(int slot, Command command) {
        commands[slot] = command;
    }

    public void startCommand(int slot) {
        commands[slot].execute();
    }
}
