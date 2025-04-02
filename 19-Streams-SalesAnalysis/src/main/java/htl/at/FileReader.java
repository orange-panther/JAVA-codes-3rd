package htl.at;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Sale> readSalesFormFile(Path filePath) {
        List<Sale> sales = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (int i = 1; i < lines.size(); i++) {
                Sale sale = processLine(lines.get(i));
                if (sale != null) {
                    sales.add(sale);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sales;
    }

    private static Sale processLine(String line) {
        String[] data = line.split(";");
        Sale sale = null;

        if (data.length != 10) {
            System.err.println("Invalid format: " + line);
            return null;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
            sale = new Sale(
                    Integer.parseInt(data[0]),
                    data[1],
                    LocalDate.parse(data[2], formatter),
                    Integer.parseInt(data[3]),
                    data[4],
                    Integer.parseInt(data[5]),
                    data[6],
                    Integer.parseInt(data[7]),
                    Double.parseDouble(data[8]),
                    data[9]
            );
        } catch (Exception e) {
            System.err.println("Invalid format: " + line);
            return null;
        }

        return sale;
    }
}
