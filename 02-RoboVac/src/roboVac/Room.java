package roboVac;

public class Room {
    private Status[][] layout;
    private int robotPosX;
    private int robotPosY;

    public Room(String[] layout) {
        this.layout = new Status[layout.length][layout[0].length()];
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length(); x++) {
                this.layout[y][x] = Status.valueOfLabel(layout[y].charAt(x));
            }
        }
    }

    public void setRobot(int posX, int posY) {
        robotPosX = posX;
        robotPosY = posY;
    }

    public int getRobotPosX() {
        return robotPosX;
    }

    public int getRobotPosY() {
        return robotPosY;
    }

    public void setCleanAtRobotPosition() {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (y == robotPosY && x == robotPosX) {
                    layout[y][x] = Status.CLEAN;
                }
            }
        }
    }

    public void setAllDirty() {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (layout[y][x] != Status.WALL) {
                    layout[y][x] = Status.DIRTY;
                }
            }
        }
    }

    public Status getStatus(int posX, int posY) {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (posX == x && posY == y) {
                    return layout[y][x];
                }
            }
        }
        return null;
    }

    public boolean isClean() {
        boolean roomIsClean = true;
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (layout[y][x] == Status.DIRTY) {
                    roomIsClean = false;
                }
            }
        }
        return roomIsClean;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                if (y == robotPosY && x == robotPosX) {
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
