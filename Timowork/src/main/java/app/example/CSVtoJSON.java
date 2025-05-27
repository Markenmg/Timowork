package app.example;

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
        String input = "input1.2.csv";  // Your input CSV file path
        String jsonFile = "output1.2.json";  // Output JSON file

        File csvFile = new File(input);
        if (!csvFile.exists()) {
            System.err.println("Error: The input CSV file does not exist.");
            return;
        }

        try {
            List<String[]> rows;

            try (CSVReader reader = new CSVReader(new FileReader(input))) {
                rows = reader.readAll();
            }

            if (rows.isEmpty()) {
                System.err.println("Error: The CSV file is empty.");
                return;
            }

            String[] headers = rows.get(0);
            List<Map<String, String>> jsonList = new ArrayList<>();

            for (int i = 1; i < rows.size(); i++) {
                Map<String, String> rowMap = new HashMap<>();
                String[] row = rows.get(i);
                for (int j = 0; j < headers.length; j++) {
                    rowMap.put(headers[j], row.length > j ? row[j] : null);
                }
                jsonList.add(rowMap);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(jsonFile), jsonList);

            System.out.println("CSV file has been successfully converted to JSON!");

        } catch (CsvException | IOException e) {
            System.err.println("Error during CSV to JSON conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
