package roboVac;

public class MoveVerticalFirst implements MoveBehaviour {

    private RoboVac roboVac;
    private int dirX;
    private int dirY;

    MoveVerticalFirst(RoboVac roboVac) {
        this.roboVac = roboVac;
    }

    @Override
    public void init() {
        dirX = 1;
        dirY = 1;
    }

    @Override
    public Position getNextMove() {
        Room room = roboVac.getRoom();

        Position roboPos = room.getRobotPosition();
        Position nextMove = roboPos;

        if (room.isAccessible(new Position(roboPos.x, roboPos.y + dirY))) {
            nextMove.y += dirY;
        } else {
            dirY *= -1;
                if (room.isAccessible(new Position(roboPos.x + dirX, roboPos.y))) {
                    nextMove.x += dirX;
                } else {
                    dirX *= -1;
                }
        }

        return nextMove;
    }
}
