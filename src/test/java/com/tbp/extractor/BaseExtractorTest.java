package com.tbp.extractor;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BaseExtractorTest {

    Long getNumberOfRows(String fileName, String community) throws IOException {
        File inputFile = new File("src/main/resources/" + community + File.separator + fileName);
        long count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line != null && line.trim().startsWith("<row")) {
                    count++;
                }
            }
        }
        return count;
    }

}
