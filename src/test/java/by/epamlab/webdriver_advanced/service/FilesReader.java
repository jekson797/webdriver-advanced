package by.epamlab.webdriver_advanced.service;

import java.io.*;

public class FilesReader {

    public String readFile(String fileUrl) {
        StringBuilder fileContent = new StringBuilder();
        File file = new File(fileUrl);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null) {
                fileContent.append(line).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
