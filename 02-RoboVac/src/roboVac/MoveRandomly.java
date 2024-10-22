package roboVac;

import java.util.Random;

public class MoveRandomly implements MoveBehaviour {
    private int dirX;
    private int dirY;
    private final Random rand = new Random();

    private static final int[][] DIRECTIONS = {
            {0, -1}, //North
            {1, 0}, //East
            {0, 1}, //South
            {-1, 0} //West
    };

    public MoveRandomly() {
        init();
    }


    @Override
    public void init() {
        changeDirection();
    }

    @Override
    public void move(RoboVac roboVac) {
        int roboX = roboVac.getRoom().getRobotPosX();
        int roboY = roboVac.getRoom().getRobotPosY();

        if (rand.nextInt(10) == 0) {
            changeDirection();
        }

        int nextX = roboX + dirX;
        int nextY = roboY + dirY;
        while (roboVac.getRoom().getStatus(nextX, nextY) == Status.WALL) {
            changeDirection();
            nextX = roboX + dirX;
            nextY = roboY + dirY;
        }
        roboVac.setPosition(nextX, nextY);
    }

    private void changeDirection() {
        //choose dir randomly
        int dir = rand.nextInt(DIRECTIONS.length);
        dirX = DIRECTIONS[dir][0];
        dirY = DIRECTIONS[dir][1];
    }
}
