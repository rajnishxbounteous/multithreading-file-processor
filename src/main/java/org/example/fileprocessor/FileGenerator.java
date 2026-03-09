package org.example.fileprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public static void generateFiles(String folderPath, int count) {
        File dir = new File(System.getProperty("user.dir") + File.separator + folderPath);
        if (!dir.exists()) {
            dir.mkdirs(); // create the folder if it doesn't exist
        }

        for (int i = 1; i <= count; i++) {
            File file = new File(dir, "file" + i + ".txt");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("This is file number " + i + "\n");
                writer.write("It contains some sample text.\n");
                writer.write("Line three with more words.\n");
                writer.write("This is file number " + i + "\n");
                writer.write("It contains some sample text.\n");
                writer.write("Line three with more words.\n");
            } catch (IOException e) {
                System.err.println("Error creating file: " + file.getName());
            }
        }
        System.out.println(count + " files generated in " + folderPath + " folder.");
    }
}
