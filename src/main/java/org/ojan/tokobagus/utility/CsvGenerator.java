import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvGenerator {

    public static void main(String[] args) {
        String fileName = "example.csv";

        try (FileWriter writer = new FileWriter(fileName);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Email", "Phone"))) {
            csvPrinter.printRecord("John Doe", "john@example.com", "123456789");
            csvPrinter.printRecord("Jane Smith", "jane@example.com", "987654321");
            System.out.println("File CSV telah dibuat!");
        } catch (IOException e) {
            System.err.println("Gagal membuat file CSV: " + e.getMessage());
        }
    }
}
