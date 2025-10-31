package co.edu.umanizales.motorcycle_workshop.service;

import co.edu.umanizales.motorcycle_workshop.model.Saveable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling CSV file operations
 */
@Service
public class CSVService {
    private static final String CSV_DIRECTORY = "data";

    public CSVService() {
        // Create data directory if it doesn't exist
        File directory = new File(CSV_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Save a single object to CSV file
     */
    public void saveToCSV(Saveable object, String fileName) throws IOException {
        String filePath = CSV_DIRECTORY + File.separator + fileName + ".csv";
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8, true)) {
            // Write header if file is new
            if (!file.exists() || file.length() == 0) {
                writer.append(object.getCSVHeader()).append("\n");
            }
            writer.append(object.toCSV()).append("\n");
            writer.flush();
        }
    }

    /**
     * Save multiple objects to CSV file
     */
    public void saveListToCSV(List<? extends Saveable> objects, String fileName) throws IOException {
        if (objects.isEmpty()) {
            return;
        }

        String filePath = CSV_DIRECTORY + File.separator + fileName + ".csv";
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8, false)) {
            // Write header
            writer.append(objects.get(0).getCSVHeader()).append("\n");

            // Write data
            for (Saveable object : objects) {
                writer.append(object.toCSV()).append("\n");
            }
            writer.flush();
        }
    }

    /**
     * Read CSV file and return lines
     */
    public List<String> readCSV(String fileName) throws IOException {
        String filePath = CSV_DIRECTORY + File.separator + fileName + ".csv";
        File file = new File(filePath);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
    }

    /**
     * Delete CSV file
     */
    public boolean deleteCSV(String fileName) {
        String filePath = CSV_DIRECTORY + File.separator + fileName + ".csv";
        File file = new File(filePath);
        return file.delete();
    }

    /**
     * Check if CSV file exists
     */
    public boolean csvExists(String fileName) {
        String filePath = CSV_DIRECTORY + File.separator + fileName + ".csv";
        File file = new File(filePath);
        return file.exists();
    }
}
