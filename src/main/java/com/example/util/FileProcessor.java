package com.example.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileProcessor {
    private final Arguments config;
    private final Statistics stats = new Statistics();

    public FileProcessor(Arguments config) {
        this.config = config;
    }

    public void processFiles() {
        for (String file : config.inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processLine(line.trim());
                }
            } catch (IOException e) {
                System.err.println("Ошибка при чтении файла " + file + ": " + e.getMessage());
            }
        }
    }

    private void processLine(String line) {
        if (line.isEmpty()) return;

        // пробуем как целое
        try {
            long val = Long.parseLong(line);
            writeToFile("integers.txt", val + "\n");
            stats.addInteger(val);
            return;
        } catch (NumberFormatException ignored) {}

        // пробуем как вещественное
        try {
            double val = Double.parseDouble(line);
            if (line.contains(".") || line.contains("E") || line.contains("e")) {
                writeToFile("floats.txt", val + "\n");
                stats.addFloat(val);
                return;
            }
        } catch (NumberFormatException ignored) {}

        // иначе строка
        writeToFile("strings.txt", line + "\n");
        stats.addString(line);
    }

    private void writeToFile(String baseName, String content) {
        try {
            Path dir = Path.of(config.outputDir);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            String fileName = config.prefix + baseName;
            Path filePath = dir.resolve(fileName);

            try (FileWriter writer = new FileWriter(filePath.toFile(), config.appendMode)) {
                writer.write(content);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл " + baseName + ": " + e.getMessage());
        }
    }

    public void printStatistics() {
        stats.print(config.fullStats);
    }
}
