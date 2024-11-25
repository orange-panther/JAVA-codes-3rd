import java.time.Duration;
import java.time.Instant;

public class CounterExt implements Runnable {
    public static volatile int totalCount = 0;
    public int maxTotalCount;
    public String name;

    public CounterExt(String name, int max) {
        this.name = name;
        this.maxTotalCount = max;
    }

    @Override
    public void run() {
        // erhöht totalCount um 1 solange totalCount < maxTotalCount
        System.out.println("Run läuft");
        int threadCounter = 0;

        Instant start = Instant.now();

        while (CounterExt.totalCount < maxTotalCount) {
            synchronized (CounterExt.class) { // Synchronisation, um Datenkorruption zu vermeiden
                if (CounterExt.totalCount < maxTotalCount) {
                    synchronized (this) {
                        CounterExt.totalCount += 1;
                        threadCounter++;
                    }
                }
            }
        }

        Instant end = Instant.now();

        var elapsedTime = Duration.between(start, end).toMillis();
        System.out.printf("Name: %s, threadCount: %d, totalCount: %d, elapsedTime: %d milliseconds%n", name, threadCounter, totalCount, elapsedTime);
    }
}
