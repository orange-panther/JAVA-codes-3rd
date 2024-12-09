package runnableSushi;

public class RunnableSushi {

    public static void main(String[] args) {
        System.out.println("Runnable Sushi opens ...");

        // Create and start belt
        var belt = new Belt(9);
        belt.start();

        // Create and start cooks

        // Create and start guests

        // Wait until the Runnable Sushi has to close

        // Stop the cooks

        // Stop the guests

        // Clean the belt

        // Stop the belt
        belt.interrupt();

        // Close Runnable Sushi
        try {
            belt.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
