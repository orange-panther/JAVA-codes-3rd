package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LotteryAnalyzerTask implements Runnable {
    private Path filePath;
    private List<Integer> winningNumbers;
    private static final Object consoleLock = new Object();

    public LotteryAnalyzerTask(Path filePath, List<Integer> winningNumbers) {
        this.filePath = filePath;
        this.winningNumbers = winningNumbers;
    }

    private List<LotteryTip> readLotteryTipsFromFile(Path filePath) {
        List<String> lines = null;
        List<LotteryTip> tips = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines) {
            tips.add(processLine(line));
        }
        return tips;
    }


    private LotteryTip processLine(String line) {
        var data = line.split(",");
        LotteryTip tip = null;
        try {
            tip = new LotteryTip(
                    data[0],
                    new int[]{
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]),
                            Integer.parseInt(data[3]),
                            Integer.parseInt(data[4]),
                            Integer.parseInt(data[5]),
                            Integer.parseInt(data[6]),
                    }
            );
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in line: " + line);
        }
        return tip;
    }

    private int getCorrectCount(LotteryTip tip) {
        int[] tipNumbers = tip.numbers();
        int correctNumbers = 0;
        for (int i = 0; i < winningNumbers.size(); i++) {
            if (tipNumbers[i] == winningNumbers.get(i)) {
                correctNumbers++;
            }
        }
        return correctNumbers;
    }

    @Override
    public void run() {
        List<LotteryTip> tips = readLotteryTipsFromFile(filePath);
        for (LotteryTip tip : tips) {
            int corrNumCount = getCorrectCount(tip);
            if (corrNumCount >= 5) {
                String threadName = Thread.currentThread().getName();
                // synchronisieren, um die Ausgabe gleichzeitg zu machen
                synchronized (consoleLock) {
                    System.out.printf("Thread %s, %s:", threadName, tip.id());
                    for (int i = 0; i < winningNumbers.size(); i++) {
                        if (tip.numbers()[i] == winningNumbers.get(i)) {
                            System.out.printf("%3d*", tip.numbers()[i]);
                        } else {
                            System.out.printf("%3d ", tip.numbers()[i]);
                        }
                    }
                    System.out.printf(" - Corr %d\n", corrNumCount);
                }
            }
        }
    }
}
