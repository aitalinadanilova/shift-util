package com.example.util;

public class Main {
    public static void main(String[] args) {
        try {
            Arguments config = ArgumentParser.parse(args);
            FileProcessor processor = new FileProcessor(config);
            processor.processFiles();
            processor.printStatistics();
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}