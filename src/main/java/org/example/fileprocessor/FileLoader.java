package org.example.fileprocessor;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileLoader {
    public static File[] loadFiles(String folderPath) {
        File dir = new File(System.getProperty("user.dir") + File.separator + folderPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));

        // Instead of crash with NPE returns empty array.
        if (files == null) return new File[0];

        Arrays.sort(files, Comparator
                .comparingLong(File::lastModified)
                .thenComparing(File::getName));

        return files;
    }
}
