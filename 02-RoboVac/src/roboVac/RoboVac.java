package roboVac;

public class RoboVac {
    private String name;
    private Room room;
    private MoveBehaviour moveBehaviour;

    public RoboVac(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int x, int y) {
        room.setRobot(x, y);
    }

    public void printRoomStatus() {
        System.out.println(room.toString());
    }

    public void clean() {
        moveBehaviour = new MoveVerticalFirst();
        room.setAllDirty();
        room.setCleanAtRobotPosition();
        int moves = 0;

        while (!room.isClean()) {
            moveBehaviour.move(this);
            moves++;
            room.setCleanAtRobotPosition();
            printRoomStatus();
        }
        System.out.printf("Room cleaned in %d moves\n", moves);
    }
}
