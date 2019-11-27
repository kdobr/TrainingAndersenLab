package task2.com.company;

enum Orientation {

    NORTH {
        @Override
        public void moveForward(Position position) {
            position.setY(position.getY() + 1);
        }

        @Override
        public void turnClockwise(Tractor tractor) {
            tractor.setOrientation(Orientation.EAST);
        }
    },
    SOUTH {
        @Override
        public void moveForward(Position position) {
            position.setY(position.getY() - 1);
        }

        @Override
        public void turnClockwise(Tractor tractor) {
            tractor.setOrientation(Orientation.WEST);
        }
    },
    EAST {
        @Override
        public void moveForward(Position position) {
            position.setX(position.getX() + 1);
        }

        @Override
        public void turnClockwise(Tractor tractor) {
            tractor.setOrientation(Orientation.SOUTH);
        }
    },
    WEST {
        @Override
        public void moveForward(Position position) {
            position.setX(position.getX() - 1);
        }

        @Override
        public void turnClockwise(Tractor tractor) {
            tractor.setOrientation(Orientation.NORTH);
        }
    };

    public abstract void moveForward(Position position);

    public abstract void turnClockwise(Tractor tractor);
}
