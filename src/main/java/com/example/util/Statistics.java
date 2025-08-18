package com.example.util;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private final List<Long> integers = new ArrayList<>();
    private final List<Double> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void addInteger(long value) {
        integers.add(value);
    }

    public void addFloat(double value) {
        floats.add(value);
    }

    public void addString(String value) {
        strings.add(value);
    }

    public void print(boolean full) {
        if (full) {
            printFull();
        } else {
            printShort();
        }
    }

    private void printShort() {
        System.out.println("Integers: " + integers.size());
        System.out.println("Floats: " + floats.size());
        System.out.println("Strings: " + strings.size());
    }

    private void printFull() {
        printShort();

        if (!integers.isEmpty()) {
            long min = integers.stream().min(Long::compare).get();
            long max = integers.stream().max(Long::compare).get();
            long sum = integers.stream().mapToLong(Long::longValue).sum();
            double avg = (double) sum / integers.size();
            System.out.printf("Integers → min: %d, max: %d, sum: %d, avg: %.2f%n", min, max, sum, avg);
        }

        if (!floats.isEmpty()) {
            double min = floats.stream().min(Double::compare).get();
            double max = floats.stream().max(Double::compare).get();
            double sum = floats.stream().mapToDouble(Double::doubleValue).sum();
            double avg = sum / floats.size();
            System.out.printf("Floats → min: %f, max: %f, sum: %f, avg: %f%n", min, max, sum, avg);
        }

        if (!strings.isEmpty()) {
            int minLen = strings.stream().mapToInt(String::length).min().getAsInt();
            int maxLen = strings.stream().mapToInt(String::length).max().getAsInt();
            System.out.printf("Strings → min length: %d, max length: %d%n", minLen, maxLen);
        }
    }
}
