import roboVac.RoboVac;
import roboVac.Room;

public class RoboVacApp {
    public static void main(String[] args) {
        RoboVac myRoboVac = new RoboVac("RoboVac EG");
        myRoboVac.setRoom(new Room(new String[]{
                "######",
                "#    #",
                "#    #",
                "#    #",
                "######"
        }));
        myRoboVac.setPosition(1, 1);
        myRoboVac.clean();
        myRoboVac.clean();
    }
}
