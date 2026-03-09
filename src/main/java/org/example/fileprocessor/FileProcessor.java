package org.example.fileprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class FileProcessor implements Callable<FileSummary> {
    private final File file;

    public FileProcessor(File file) {
        this.file = file;
    }

    @Override
    public FileSummary call() throws Exception {
        int lines = 0, words = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                words += line.trim().isEmpty() ? 0 : line.split("\\s+").length;
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + file.getName());
        }
        return new FileSummary(file.getName(), lines, words);
    }
}
