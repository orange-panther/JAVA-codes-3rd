package roboVac;

public class MoveVerticalFirst implements MoveBehaviour {

    private int dirX;
    private int dirY;

    public MoveVerticalFirst() {
        init();
    }

    @Override
    public void init() {
        dirX = 0;
        dirY = 1;
    }

    @Override
    public void move(RoboVac roboVac) {
        int roboX = roboVac.getRoom().getRobotPosX();
        int roboY = roboVac.getRoom().getRobotPosY();

        if (getStatusOfPosition(roboVac, roboX, roboY + dirY) == Status.WALL) {
            if (getStatusOfPosition(roboVac, roboX + 1, roboY) == Status.DIRTY) {
                dirX = 1;
            } else {
                dirX = -1;
            }
            dirY = 0;
        }

        //set robot to next column
        roboVac.setPosition(roboX + dirX, roboY + dirY);
        if (dirX != 0) {
            dirX = 0;
            if (getStatusOfPosition(roboVac, roboX, roboY + 1) == Status.WALL) {
                dirY = -1;
            } else {
                dirY = 1;
            }
        }
    }

    private Status getStatusOfPosition(RoboVac roboVac, int x, int y) {
        return roboVac.getRoom().getStatus(x, y);
    }
}
