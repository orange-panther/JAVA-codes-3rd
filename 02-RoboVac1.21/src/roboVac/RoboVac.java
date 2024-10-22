package roboVac;

import jdk.jshell.spi.ExecutionControl;

public class RoboVac {
    private String name;
    private Room room;
    private int moveCount;
    private MoveBehaviour moveBehaviour;

    public RoboVac(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPosition(Position pos) {
        room.setRobotPosition(pos);
    }

    public void clean() {
        resetMoveCount();
        moveBehaviour = new MoveVerticalFirst(this);
        moveBehaviour.init();
        room.setAllDirty();
        room.setCleanAtRobotPosition();

        while (!room.isClean()) {
            if(moveBehaviour instanceof MoveToTarget) {
                moveBehaviour = new MoveVerticalFirst(this);
                moveBehaviour.init();
            }

            var nextMove = moveBehaviour.getNextMove();

            if(room.isClean(nextMove) || !room.isAccessible(nextMove)) {
                moveToTarget(room.getNearestDirtyPosition(room.getRobotPosition()));
            } else {
                setPosition(nextMove);
                room.setCleanAtRobotPosition();
                printRoomStatus();
                increaseMoveCount();
            }
        }
        System.out.printf("Room cleaned in %d moves\n", moveCount);
    }

    public void moveToTarget(Position pos) {
        moveBehaviour = new MoveToTarget(this, pos);
        moveBehaviour.init();
        while(!this.room.getRobotPosition().equals(pos)) {
            setPosition(moveBehaviour.getNextMove());
            room.setCleanAtRobotPosition();
            increaseMoveCount();
            printRoomStatus();
        }
    }

    public void printRoomStatus() {
        System.out.println(room.getLayout());
    }

    private void resetMoveCount() {
        this.moveCount = 0;
    }

    private void increaseMoveCount() {
        this.moveCount++;
    }
}
