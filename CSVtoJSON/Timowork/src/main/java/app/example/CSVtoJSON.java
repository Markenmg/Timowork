package app.example;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import static spark.Spark.get;
import static spark.Spark.port;

public class CSVtoJSON {
    public static void main(String[] args) {
        port(4570);

        get("/", (req, res) -> {
            List<Map<String, String>> jsonList = new ArrayList<>();

            try (CSVReader reader = new CSVReaderBuilder(
                    new InputStreamReader(CSVtoJSON.class.getResourceAsStream("/input.csv")))
                    .withCSVParser(new CSVParserBuilder().withSeparator('\t').build())
                    .build()) {

                List<String[]> rows = reader.readAll();
                if (rows.isEmpty()) return "CSV is empty.";

               String[] headers = new String[] {"id", "name", "q1", "q2", "q3", "q4", "q5", "empty", "price", "brand", "code"};


                for (int i = 1; i < rows.size(); i++) {
                    Map<String, String> rowMap = new LinkedHashMap<>();
                    String[] row = rows.get(i);
                    for (int j = 0; j < headers.length; j++) {
                        rowMap.put(headers[j], j < row.length ? row[j].replace("\"", "") : "");
                    }
                    jsonList.add(rowMap);
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            res.type("application/json");
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonList);
        });
    }
}
