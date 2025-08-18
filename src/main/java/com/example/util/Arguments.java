package com.example.util;


import java.util.ArrayList;
import java.util.List;

public class Arguments {
    public boolean appendMode = false;      // -a
    public boolean fullStats = false;       // -f
    public boolean shortStats = false;      // -s
    public String outputDir = ".";          // -o
    public String prefix = "";              // -p
    public List<String> inputFiles = new ArrayList<>();
}
