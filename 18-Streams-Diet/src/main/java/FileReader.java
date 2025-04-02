import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileReader {

    public static List<Person> readPersonsFromFile(Path filePath) {
        List<Person> people = new ArrayList<>();
        List<String> lines = null;

        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i < lines.size(); i++) {
            people.add(processLine(lines.get(i)));
        }

        return people;
    }

    private static Person processLine(String line) {
        String[] data = line.split(";");
        Person person = null;
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd.MM.uuuu");

        if (data.length != 8) {
            throw new IllegalArgumentException("Invalid data size. Expected 10 elements, got " + data.length);
        }

        try {
            person = new Person(
                    Integer.parseInt(data[0]),
                    data[1],
                    data[2],
                    data[3].charAt(0),
                    LocalDate.parse(data[4], dateFormater),
                    Integer.parseInt(data[5]),
                    Double.parseDouble(data[6]),
                    Double.parseDouble(data[7])
            );
        } catch (Exception e) {
            System.out.println("Input was not in the right format:" + line);
            e.printStackTrace();
        }
        return person;
    }

}
