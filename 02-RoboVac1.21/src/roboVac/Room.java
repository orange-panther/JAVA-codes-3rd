package roboVac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
    private Layout layout;
    private Position robotPos;

    public Room(String[] layout) {
        this.layout = new Layout(layout);
    }

    public boolean isWall(Position pos) {
        return layout.getStatus(pos) == Status.WALL;
    }

    public boolean isDirty(Position pos) {
        return layout.getStatus(pos) == Status.DIRTY;
    }

    public boolean isClean(Position pos) {
        return layout.getStatus(pos) == Status.CLEAN;
    }

    public boolean isAccessible(Position pos) {
        return layout.isValid(pos) && !isWall(pos);
    }

    public boolean isClean() {
        boolean roomIsClean = true;
        for (Position pos : layout.positions) {
            if (isAccessible(pos) && layout.getStatus(pos) == Status.DIRTY) {
                roomIsClean = false;
            }
        }
        return roomIsClean;
    }

    public void setAllDirty() {
        var positions = layout.getAllPosition();
        for (Position pos : positions) {
            if (isAccessible(pos)) {
                layout.setStatus(pos, Status.DIRTY);
            }
        }
    }

    public Position getRobotPosition() {
        return this.robotPos;
    }

    public void setRobotPosition(Position pos) {
        this.robotPos = pos;
    }

    public void setCleanAtRobotPosition() {
        this.layout.setStatus(robotPos, Status.CLEAN);
    }

    public int[][] getDistanceMatrix(Position pos) {
        int[][] matrix = new int[layout.getHeight()][layout.getWidth()];
        List<Position> queue = new ArrayList<>();

        for (int[] row : matrix) {
            Arrays.fill(row, -1);
        }

        matrix[pos.y][pos.x] = 0;

        do {
            var neighbours = pos.getNeighbours();
            int numToWrite = matrix[pos.y][pos.x] + 1;
            for (var curPos : neighbours) {
                if (isAccessible(curPos) && matrix[curPos.y][curPos.x] == -1) {
                    queue.add(curPos);
                    matrix[curPos.y][curPos.x] = numToWrite;
                }
            }
            pos = queue.getFirst();
            queue.removeFirst();
        } while (!queue.isEmpty());

        return matrix;
    }

    public Position getNearestDirtyPosition(Position pos) {
        var matrix = getDistanceMatrix(pos);
        Position nearestDirty = null;

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                var currentPos = new Position(x, y);
                if (isAccessible(currentPos) && isDirty(currentPos)) {
                    if (nearestDirty == null) {
                        nearestDirty = currentPos;
                    } else {
                        if (matrix[y][x] < matrix[nearestDirty.y][nearestDirty.x] && matrix[y][x] != -1) {
                            nearestDirty = currentPos;
                        }
                    }
                }

            }
        }
        return nearestDirty;
    }

    public String getLayout() {
        return this.layout.toString();
    }

    public void printDistanceMatrix(Position pos) {
        var matrix = getDistanceMatrix(pos);
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.println(matrix[y][x]);
            }
        }
    }

    private class Layout {

        public Status[][] layout;
        public Position[] positions;

        public Layout(String[] layout) {
            this.layout = new Status[layout.length][layout[0].length()];
            for (int y = 0; y < layout.length; y++) {
                for (int x = 0; x < layout[0].length(); x++) {
                    this.layout[y][x] = Status.valueOfLabel(layout[y].charAt(x));
                }
            }
        }

        public int getHeight() {
            return layout.length;
        }

        public int getWidth() {
            return layout[0].length;
        }

        public Status getStatus(Position pos) {
            for (int y = 0; y < layout.length; y++) {
                for (int x = 0; x < layout[y].length; x++) {
                    if (pos.x == x && pos.y == y) {
                        return layout[y][x];
                    }
                }
            }
            return null;
        }

        public void setStatus(Position pos, Status stat) {
            layout[pos.y][pos.x] = stat;
        }

        // schaut ob Position exisitiert im Layout (ob Indexüberschreitung ist; wenn Raum nicht von Wänden eingegrenzt ist)
        public boolean isValid(Position pos) {
            if (pos.y > layout.length || pos.y < 0) {
                if (pos.x > layout[0].length || pos.x < 0) {
                    return false;
                }
            }
            return true;
        }

        public Position[] getAllPosition() {
            int index = 0;

            if (positions == null) {
                positions = new Position[layout.length * layout[0].length];

                for (int y = 0; y < layout.length; y++) {
                    for (int x = 0; x < layout[y].length; x++) {
                        positions[index++] = new Position(x, y);
                    }
                }
            }

            return positions;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < layout.length; y++) {
                for (int x = 0; x < layout[y].length; x++) {
                    if (y == robotPos.y && x == robotPos.x) {
                        sb.append('r');
                    } else {
                        sb.append(layout[y][x].label);
                    }
                }
                sb.append('\n');
            }
            return sb.toString();
        }
    }
}
