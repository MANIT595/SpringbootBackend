package com.manikanta.microservices.project.ProductService.Schduler;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

@Service
public class CsvFileProcessorService {

    private static final String DIRECTORY_PATH = "ProductService/src/main/resources";

    @Scheduled(fixedRate = 6000000)  // Check every minute
    public void checkForNewCsvFiles() {
        System.out.println("Checking for new CSV files...");

        try {
            File folder = new File(DIRECTORY_PATH);
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

            if (files != null && files.length > 0) {
                Arrays.stream(files).forEach(this::processCsvFile);
            } else {
                System.out.println("No new CSV files found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processCsvFile(File file) {
        System.out.println("Processing file: " + file.getName());

        try (BOMInputStream bomInputStream = new BOMInputStream(new FileInputStream(file));
             Reader reader = new InputStreamReader(bomInputStream)) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("S.NO", "ItemName", "Quantity", "Price", "Description")
                    .withFirstRecordAsHeader()
                    .parse(reader);

            for (CSVRecord record : records) {
                String serialNo = record.get("S.NO");
                String itemName = record.get("ItemName");
                String quantity = record.get("Quantity");
                String price = record.get("Price");
                String description = record.get("Description");

                // Process the data as needed
                System.out.println("Serial No: " + serialNo + ", Item Name: " + itemName + ", Quantity: " + quantity + ", Price: " + price + ", Description: " + description);
            }

            // Move the file to a processed directory
            moveFileToProcessedFolder(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moveFileToProcessedFolder(File file) throws IOException {
        Path processedDirectory = Paths.get(DIRECTORY_PATH, "processed");
        if (!Files.exists(processedDirectory)) {
            Files.createDirectory(processedDirectory);
        }
        Files.move(file.toPath(), processedDirectory.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Moved file to processed folder: " + file.getName());
    }
}
