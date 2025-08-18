package com.example.util;

public class ArgumentParser {

    public static Arguments parse(String[] args) {
        Arguments config = new Arguments();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-a" -> config.appendMode = true;
                case "-s" -> config.shortStats = true;
                case "-f" -> config.fullStats = true;
                case "-o" -> {
                    if (i + 1 < args.length) {
                        config.outputDir = args[++i];
                    } else {
                        throw new IllegalArgumentException("Опция -o требует путь");
                    }
                }
                case "-p" -> {
                    if (i + 1 < args.length) {
                        config.prefix = args[++i];
                    } else {
                        throw new IllegalArgumentException("Опция -p требует префикс");
                    }
                }
                default -> {
                    config.inputFiles.add(arg);
                }
            }
        }

        if (!config.shortStats && !config.fullStats) {
            config.shortStats = true; // по умолчанию краткая статистика
        }

        if (config.inputFiles.isEmpty()) {
            throw new IllegalArgumentException("Не указаны входные файлы!");
        }

        return config;
    }
}
