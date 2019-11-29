package task2.com.company;

import task2.com.company.command.MoveForwardCommand;
import task2.com.company.command.TurnClockwiseCommand;


public class Main {

    public static void main(String[] args) {
        Field field_5_5 = new Field(5, 5);
        Tractor tractor = new Tractor(field_5_5, new Position(1, 1));
        tractor.setOrientation(Orientation.NORTH);
        RemoteControl rm = new RemoteControl();
        rm.setCommand(0, new MoveForwardCommand(tractor));
        rm.setCommand(1, new TurnClockwiseCommand(tractor));

            for (int i = 0; i < 4; i++) {
                rm.startCommand(0);
            }
        System.out.println(tractor.getPosition().getX()+" "+tractor.getPosition().getY());
           rm.startCommand(1);
        for (int i = 0; i < 4; i++) {
            rm.startCommand(0);
        }
        System.out.println(tractor.getPosition().getX()+" "+tractor.getPosition().getY());
        rm.startCommand(1);
        for (int i = 0; i < 4; i++) {
            rm.startCommand(0);
        }
        System.out.println(tractor.getPosition().getX()+" "+tractor.getPosition().getY());
        rm.startCommand(1);
        for (int i = 0; i < 4; i++) {
            rm.startCommand(0);
        }
        System.out.println(tractor.getPosition().getX()+" "+tractor.getPosition().getY());

        }

}
