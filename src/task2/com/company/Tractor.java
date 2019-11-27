package task2.com.company;

public class Tractor {

    private static Field field;
    private Position position;
    private Orientation orientation;


    public Tractor(Field field, Position position) {
        this.position = position;
        this.field = field;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void moveForward() {
        orientation.moveForward(position);
        if (position.getX() < 1 || position.getX() > field.getX() ||
                position.getY() < 1 || position.getY() > field.getY()) {
            throw new TractorInDitchException("Tractor in the ditch");
        }
    }

    public void turnClockwise() {
        orientation.turnClockwise(this);
    }
}
