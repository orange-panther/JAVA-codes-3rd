public class Main {
    public static void main(String[] args) {
        final int upperBound = 100000;
        Thread tick = new Thread(new CounterExt("Tick",upperBound), "Tick");
        Thread trick = new Thread(new CounterExt("Trick",upperBound), "Trick");
        Thread track = new Thread(new CounterExt("Track",upperBound), "Track");

        tick.start();
        trick.start();
        track.start();

        try {
            tick.join();
            trick.join();
            track.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main ist fertig");

    }
}