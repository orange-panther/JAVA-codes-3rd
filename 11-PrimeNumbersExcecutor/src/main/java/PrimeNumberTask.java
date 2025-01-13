public class PrimeNumberTask implements Runnable {

    private final int startRange;
    private final int endRange;
    private int colorIndex = 0;

    public PrimeNumberTask(int startRange, int endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        colorIndex = Integer.parseInt(threadName.substring(threadName.lastIndexOf('-') + 1));
        String color = ConsoleColor.values()[colorIndex].ansiColor();
        for (int i = startRange; i <= endRange; i++) {
            if (isPrime(i)) {
                System.out.printf("%sThread %s found prime number: %d%n", color, threadName, i);
            }
        }
    }

    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}