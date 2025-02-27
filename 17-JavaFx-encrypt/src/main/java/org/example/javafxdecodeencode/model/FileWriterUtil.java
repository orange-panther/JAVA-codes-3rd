package org.example.javafxdecodeencode.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterUtil {

    public static void writeToFile(String file, String text){
        final String DIRECTORY_PATH = "files";
        File dir =new File(DIRECTORY_PATH);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    String line = text + "\n";
                    writer.write(text);
                    writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred when writing to file '" + file + "'");
            e.printStackTrace();
        }
    }
}
