package com.chrisp1985.adventofcode.libs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {


    public List<String> readLinesOfFile(String inputFilePath) {

        var resourcePath = Paths.get("src", "test", "resources", inputFilePath);

        List<String> stringList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resourcePath.toAbsolutePath().toString()))) {
            String line = br.readLine();
            while (line != null) {
                stringList.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringList;
    }
}
