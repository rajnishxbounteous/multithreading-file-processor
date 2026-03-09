package org.example.fileprocessor;

import java.util.List;

public class SummaryReport {
    public static void printReport(List<FileSummary> statsList) {
        int totalFiles = statsList.size();
        int totalLines = statsList.stream().mapToInt(FileSummary::getLines).sum();
        int totalWords = statsList.stream().mapToInt(FileSummary::getWords).sum();

        System.out.println("----------------------------------");
        System.out.println("Summary");
        System.out.println("----------------------------------");
        for (FileSummary stats : statsList) {
            System.out.println("File: " + stats.getFileName());
            System.out.println("Lines: " + stats.getLines());
            System.out.println("Words: " + stats.getWords());
            System.out.println();
        }
        System.out.println("Total Files Processed: " + totalFiles);
        System.out.println("Total Lines: " + totalLines);
        System.out.println("Total Words: " + totalWords);
    }
}
