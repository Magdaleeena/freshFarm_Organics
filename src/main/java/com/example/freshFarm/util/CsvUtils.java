package com.example.freshFarm.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class CsvUtils {

    /**
     * Parses a CSV file into a list of row maps (column name → value).
     */
    public static List<Map<String, String>> parseCsv(String filePath) {
        List<Map<String, String>> rows = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : parser) {
                Map<String, String> row = record.toMap();
                rows.add(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
