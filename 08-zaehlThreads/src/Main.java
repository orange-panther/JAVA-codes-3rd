public class Main {
    public static void main(String[] args) {
        final int upperBound = 1000;
        Thread tick = new CounterExt("Tick",upperBound);
        Thread trick = new CounterExt("Trick",upperBound);
        Thread track = new CounterExt("Track",upperBound);

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