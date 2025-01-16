package analyzer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LotteryAnalyzer {
    private static int threadPoolSize;

    public static void main(String[] args) {
        final List<Integer> WINNING_NUMBERS = List.of(2,3,4,22,24,25);

        threadPoolSize = Runtime.getRuntime().availableProcessors();
        List<Path> files;

        final String DIRECTORY_PATH = "files"; // Directory with the files to process
        Path directoryPath = Paths.get(DIRECTORY_PATH);

        // abort if the directory does not exist
        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
            System.err.printf("Directory '%s' does not exist.%n", DIRECTORY_PATH);
            return;
        }

        try {
            files = LotteryAnalyzer.getAllFiles(directoryPath, "txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        for (Path file : files) {
            executorService.submit(new LotteryAnalyzerTask(file, WINNING_NUMBERS));
        }

        executorService.shutdown();
    }

    public static List<Path> getAllFiles(Path directory, String extension) throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*" +
                extension)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) { // Ensure it's a file, not a directory
                    result.add(entry);
                }
            }
        }
        return result;
    }
}

