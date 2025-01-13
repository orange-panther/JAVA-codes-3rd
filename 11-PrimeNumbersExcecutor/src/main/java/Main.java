import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    // Constants
    private static final int LOWER_LIMIT = 1; // Starting range
    private static final int UPPER_LIMIT = 1000; // Ending range
    private static final int NUMBER_OF_TASKS = 10; // Number of tasks
    private static final int THREAD_POOL_SIZE = 4; // Number of threads in the pool

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        int rangePerTask = (UPPER_LIMIT - LOWER_LIMIT + 1) / NUMBER_OF_TASKS;

        // Create and submit tasks
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            // calculate range of one task
            int start = LOWER_LIMIT + i * rangePerTask;
            int end = (i == NUMBER_OF_TASKS - 1) ? UPPER_LIMIT : start + rangePerTask - 1;

            executorService.submit(new PrimeNumberTask(start, end));
        }

        // Shut down the executor service
        executorService.shutdown();
    }
}

