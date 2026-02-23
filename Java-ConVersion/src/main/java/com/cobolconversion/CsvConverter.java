package com.cobolconversion;

import java.io.*;
import java.nio.file.*;

/**
 * Converted from: csvconverter.cbl
 * Original Author: Queen of Cobol
 * Date: March 2, 2015
 * Purpose: Read CSV with tilde delimiter and convert to formatted output
 */
public class CsvConverter {
    
    // Record to represent address data (using Java 21 record)
    record AddressData(
        String lastName,
        String firstName,
        String street,
        String city,
        String state,
        String zip
    ) {
        String toFormattedString() {
            return String.format("%-15s     %-10s     %-25s     %-10s     %-2s     %-10s",
                    padOrTrim(lastName, 15),
                    padOrTrim(firstName, 10),
                    padOrTrim(street, 25),
                    padOrTrim(city, 10),
                    padOrTrim(state, 2),
                    padOrTrim(zip, 10));
        }
        
        private static String padOrTrim(String str, int length) {
            if (str == null) str = "";
            if (str.length() > length) {
                return str.substring(0, length);
            }
            return String.format("%-" + length + "s", str);
        }
    }
    
    public static void main(String[] args) {
        String inputFile = "datain.txt";
        String outputFile = "dataout.txt";
        
        // Allow command line arguments for input/output files
        if (args.length >= 1) {
            inputFile = args[0];
        }
        if (args.length >= 2) {
            outputFile = args[1];
        }
        
        try {
            processFile(inputFile, outputFile);
            System.out.println("CSV conversion completed successfully.");
            System.out.println("Input: " + inputFile);
            System.out.println("Output: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void processFile(String inputFile, String outputFile) throws IOException {
        Path inputPath = Paths.get(inputFile);
        Path outputPath = Paths.get(outputFile);
        
        // Check if input file exists
        if (!Files.exists(inputPath)) {
            throw new FileNotFoundException("Input file not found: " + inputFile);
        }
        
        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                
                AddressData data = parseLine(line);
                writer.write(data.toFormattedString());
                writer.newLine();
            }
        }
    }
    
    private static AddressData parseLine(String line) {
        // Split by tilde delimiter (~ in COBOL UNSTRING)
        String[] parts = line.split("~", -1);
        
        // Ensure we have 6 parts, fill with empty strings if not
        String[] fields = new String[6];
        for (int i = 0; i < 6; i++) {
            fields[i] = (i < parts.length) ? parts[i].trim() : "";
        }
        
        return new AddressData(
            fields[0], // lastName
            fields[1], // firstName
            fields[2], // street
            fields[3], // city
            fields[4], // state
            fields[5]  // zip
        );
    }
}
