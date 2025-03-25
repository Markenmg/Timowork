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
    public static void main(String[] args) throws CsvException {
        String input = "input1.2.csv";  // Replace with your CSV file path
        String jsonFile = "output1.2.json"; // Replace with your output JSON file path

        try {
            List<String[]> rows;
            try ( // Read CSV file
                    CSVReader reader = new CSVReader(new FileReader(input))) {
                rows = reader.readAll();
            }

            // Assuming the first row contains the header
            String[] headers = rows.get(0);

            // Convert CSV rows to a List of Maps
            List<Map<String, String>> jsonList = new ArrayList<>();
            for (int i = 1; i < rows.size(); i++) {
                Map<String, String> rowMap = new HashMap<>();
                String[] row = rows.get(i);
                for (int j = 0; j < headers.length; j++) {
                    rowMap.put(headers[j], row[j]);
                }
                jsonList.add(rowMap);
            }

            // Write JSON file using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(jsonFile), jsonList);

            System.out.println("CSV file has been successfully converted to JSON!");
        } catch (IOException e) {
        }
    }
}
