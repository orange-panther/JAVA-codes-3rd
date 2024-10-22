package roboVac;

import java.util.Objects;

public class Position {

    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position[] getNeighbours() {
        var neighbours = new Position[4];
        neighbours[0] = new Position(x, y - 1); // North
        neighbours[1] = new Position(x + 1, y); // East
        neighbours[2] = new Position(x, y + 1); // South
        neighbours[3] = new Position(x - 1, y); // West
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
}
