import roboVac.Position;
import roboVac.RoboVac;
import roboVac.Room;

public class RoboVacApp {
    public static void main(String[] args) {
        RoboVac myRoboVac = new RoboVac("RoboVac EG");
        String[] moveToTargetRoom = new String[]{
                "##########",
                "#        #",
                "#        #",
                "#     ## #",
                "#     #  #",
                "##########"
        };
        String[] complicatedRoom = new String[]{
                "#########",
                "#       #",
                "#    ## #",
                "#    #  #",
                "#########"
        };
        String[] easyRoom = new String[]{
                "######",
                "#    #",
                "#    #",
                "#    #",
                "######"
        };

        myRoboVac.setRoom(new Room(moveToTargetRoom));
        myRoboVac.setPosition(new Position(1, 1));
        myRoboVac.clean();
        myRoboVac.clean();
    }
}
