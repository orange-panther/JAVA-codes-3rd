package roboVac;

import java.util.Random;

public class MoveRandomly implements MoveBehaviour {
    private final Random rand = new Random();
    private RoboVac roboVac;
    private int dirIndex;

    public MoveRandomly(RoboVac robot) {
        init();
        this.roboVac = robot;
    }

    @Override
    public void init() {
        dirIndex = 0; // set to north
    }

    @Override
    public Position getNextMove() {
        Position roboPos = roboVac.getRoom().getRobotPosition();
        var neighbours = roboPos.getNeighbours();

        if (rand.nextInt(10) == 0) {
            dirIndex = rand.nextInt(neighbours.length);
        }
        return neighbours[dirIndex];
    }
}
