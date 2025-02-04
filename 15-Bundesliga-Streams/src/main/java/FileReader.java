import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<Match> readMatchesFromFile(Path filePath) {
        List<Match> matches = new ArrayList<>();
        List<String> lines = null;

        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i < lines.size(); i++) {
            matches.add(processLine(lines.get(i)));
        }

        return matches;
    }

    public static Match processLine(String line){
        String[] data = line.split(",");

        if(data.length != 10){
            throw new IllegalArgumentException("Invalid data size. Expected 10 elements, got " + data.length);
        }
        Match match = null;

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
            match = new Match(
                    data[0],
                    Integer.parseInt(data[1]),
                    LocalDate.parse(data[2], dateFormatter),
                    LocalTime.parse(data[3]),
                    data[4],
                    data[5],
                    Integer.parseInt(data[6]),
                    Integer.parseInt(data[7]),
                    Integer.parseInt(data[8]),
                    Integer.parseInt(data[9])
            );
        } catch (Exception e) {
            System.out.println("Input was not in the right format:" + line);
            e.printStackTrace();
        }
        return match;
    }
}

