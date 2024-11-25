import java.time.Duration;
import java.time.Instant;

public class CounterExt extends Thread {
    public static volatile int totalCount = 0;
    public int maxTotalCount;

    public CounterExt(String name, int max) {
        super(name);
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
                    CounterExt.totalCount += 1;
                    threadCounter++;
                }
            }
        }

        Instant end = Instant.now();

        var elapsedTime = Duration.between(start, end).toMillis();
        System.out.printf("Name: %s, threadCount: %d, totalCount: %d, elapsedTime: %d milliseconds%n", getName(), threadCounter, totalCount, elapsedTime);

        //System.out.printf("Name: %s, threadCount: %d, totalCount: %d%n", getName(), threadCounter, totalCount);
    }
}
