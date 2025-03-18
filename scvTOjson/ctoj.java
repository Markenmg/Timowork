import com.opencsv.CSVReader;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVtoJSON {
    public static void main(String[] args) {
        String csvFile = "input.csv";  // Replace with your CSV file path
        String jsonFile = "output.json"; // Replace with your output JSON file path

        try {
            // Read CSV file
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            List<String[]> rows = reader.readAll();
            reader.close();

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
            e.printStackTrace();
        }
    }
}
