package roboVac;

public class MoveToTarget implements MoveBehaviour{
    private RoboVac roboVac;
    private Position target;
    private int[][] distanceMatrix;

    MoveToTarget(RoboVac roboVac, Position target) {
        this.roboVac = roboVac;
       setTarget(target);
    }

    @Override
    public void init(){
        distanceMatrix = roboVac.getRoom().getDistanceMatrix(this.target);
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    @Override
    public Position getNextMove() {
        var roboPos = roboVac.getRoom().getRobotPosition();
        var neighbours = roboVac.getRoom().getRobotPosition().getNeighbours();
        Position nextMove = roboPos;

        for(var field: neighbours) {
            if(distanceMatrix[field.y][field.x] == distanceMatrix[roboPos.y][roboPos.x] -1
                    && roboVac.getRoom().isAccessible(field)) {
                nextMove = field;
            }
        }

        return nextMove;
    }
}
