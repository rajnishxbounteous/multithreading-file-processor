package org.example.fileprocessor;

public class FileSummary {
    private final String fileName;
    private final int lines;
    private final int words;

    public FileSummary(String fileName, int lines, int words) {
        this.fileName = fileName;
        this.lines = lines;
        this.words = words;
    }

    public String getFileName() { return fileName; }
    public int getLines() { return lines; }
    public int getWords() { return words; }
}
