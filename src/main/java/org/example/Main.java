package org.example;

import org.example.fileprocessor.FileLoader;
import org.example.fileprocessor.FileProcessor;
import org.example.fileprocessor.FileSummary;
import org.example.fileprocessor.SummaryReport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("Processing files using 4 threads...");

        // Load all files (sorted)
        File[] files = FileLoader.loadFiles("files");
        if (files.length == 0) {
            System.out.println("No text files found.");
            return;
        }

        int batchSize = 100;
        int totalFiles = files.length;

        // Process the files in batches of 100
        for (int i = 0; i < totalFiles; i += batchSize) {
            int endIndex = Math.min(i + batchSize, totalFiles);
            File[] batch = java.util.Arrays.copyOfRange(files, i, endIndex);

            System.out.println("\n--- Processing batch " + ((i / batchSize) + 1) +
                    " (" + batch.length + " files) ---");

            ExecutorService executor = Executors.newFixedThreadPool(4);
            List<Future<FileSummary>> futures = new ArrayList<>();

            for (File file : batch) {
                futures.add(executor.submit(new FileProcessor(file)));
            }

            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<FileSummary> results = new ArrayList<>();
            for (Future<FileSummary> future : futures) {
                try {
                    results.add(future.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Print report for this batch
            SummaryReport.printReport(results);
        }

        long end = System.currentTimeMillis();
        System.out.println("\nTotal Execution time: " + (end - start) + " ms");
    }
}
