package runnableSushi;

public class RunnableSushi {

    public static void main(String[] args) {
        System.out.println("Runnable Sushi opens ...");

        // Create and start belt
        var belt = new Belt(9);
        belt.start();

        // Create and start cooks
        var cookS = new Producer("S", FoodType.SUSHI, belt, 1);
        var cookA = new Producer("A", FoodType.APPETIZER, belt, 2);

        cookS.start();
        cookA.start();

        // Create and start guests
        var guestAnn = new Consumer(ConsumerType.GUEST, "Ann", belt, 3);
        var guestBob = new Consumer(ConsumerType.GUEST, "Bob", belt, 5);
        var guestJoe = new Consumer(ConsumerType.GUEST, "Joe", belt, 7);

        guestAnn.start();
        guestBob.start();
        guestJoe.start();

        // Wait until the Runnable Sushi has to close
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Stop the cooks
        cookS.interrupt();
        cookA.interrupt();

        // Stop the guests
        guestAnn.interrupt();
        guestBob.interrupt();
        guestJoe.interrupt();

        try {
            belt.join();
            cookS.join();
            cookA.join();
            guestAnn.join();
            guestBob.join();
            guestJoe.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Close Runnable Sushi
        try {
            // Clean the belt
            System.out.println("Start clean up");
            var cleaner = new Consumer(ConsumerType.CLEANER, "Cleaner", belt, 0);
            cleaner.start();

            // Stop the belt
            cleaner.join();
            belt.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Runnable Sushi closes");
    }

}
