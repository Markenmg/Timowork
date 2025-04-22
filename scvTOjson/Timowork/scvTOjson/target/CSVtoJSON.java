import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVtoJSON {
    public static void main(String[] args) {
        String input = "input1.2.csv";  // Replace with your CSV file path
        String jsonFile = "output1.2.json"; // Replace with your output JSON file path

        // Check if input file exists
        File csvFile = new File(input);
        if (!csvFile.exists()) {
            System.err.println("Error: The input CSV file does not exist.");
            return;
        }

        try {
            List<String[]> rows;

            // Read CSV file using try-with-resources
            try (CSVReader reader = new CSVReader(new FileReader(input))) {
                rows = reader.readAll();
            }

            // Check if CSV file is empty
            if (rows.isEmpty()) {
                System.err.println("Error: The CSV file is empty.");
                return;
            }

            // Assuming the first row contains the header
            String[] headers = rows.get(0);

            // Convert CSV rows to a List of Maps
            List<Map<String, String>> jsonList = new ArrayList<>();
            for (int i = 1; i < rows.size(); i++) {
                Map<String, String> rowMap = new HashMap<>();
                String[] row = rows.get(i);
                for (int j = 0; j < headers.length; j++) {
                    rowMap.put(headers[j], row.length > j ? row[j] : null);  // Handle missing values gracefully
                }
                jsonList.add(rowMap);
            }

            // Write JSON file using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(jsonFile), jsonList);

            System.out.println("CSV file has been successfully converted to JSON!");

        } catch (CsvException | IOException e) {
            // Catch and log the exception
            System.err.println("Error during CSV to JSON conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
