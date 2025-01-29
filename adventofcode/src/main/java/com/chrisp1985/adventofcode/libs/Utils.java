package com.chrisp1985.adventofcode.libs;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String readSingleLineOfFile(String inputFilePath, int lineNumber) {

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
        return stringList.get(lineNumber);
    }

    public int[] getArrayFromList(List<int[]> ints, int index) {

        int[] res = new int[ints.size()];

        for(int i = 0; i < ints.size(); i++) {
            res[i] = ints.get(i)[index];
        }

        return res;
    }


    public List<int[]> strArrToIntArray(List<String[]> stringArray) {

        List<int[]> integerArray = new ArrayList<>();

        for(String[] strArr : stringArray) {
            int[] intArr = new int[strArr.length];
            for(int i = 0; i < strArr.length; i++) {

                intArr[i] = Integer.parseInt(strArr[i]);

            }

            integerArray.add(intArr);

        }

        return integerArray;
    }

    public List<String[]> getListOfStrings(String inputFilePath) {

        List<String> inputLines = readLinesOfFile(inputFilePath);
        return inputLines
                .stream()
                .map(a -> a.split("\\s+"))
                .collect(Collectors.toList());
    }

    public int[] stringToIntArray(String inputString) {
        int[] intArr = new int[inputString.length()];
        for(int i = 0; i < intArr.length; i++) {
            intArr[i] = inputString.charAt(i) - '0';
        }
        return intArr;
    }

    public char[] stringToCharArray(String inputString) {
        return inputString.toCharArray();
    }

    public List<String> stringToStringList(String inputString) {
        List<String> res = new ArrayList<>();
        char[] charArr = inputString.toCharArray();
        for(int i=0; i < inputString.toCharArray().length; i++) {
            res.add(String.valueOf(inputString.charAt(i)));
        }
        return res;
    }

    public String[][] create2dArrayFromStringListArray(List<String[]> listArray) {
        String[][] res = new String[listArray.size()][listArray.get(0)[0].toCharArray().length];

        for(int i=0; i < listArray.size(); i++) {
            for(int j=0; j < listArray.get(i)[0].toCharArray().length; j++) {
                res[i][j] = String.valueOf(listArray.get(i)[0].toCharArray()[j]);
            }
        }
        return res;
    }

    public String[][] create2dArrayFromStringList(List<String> listArray) {
        String[][] res = new String[listArray.size()][listArray.get(0).toCharArray().length];

        for(int i=0; i < listArray.size(); i++) {
            for(int j=0; j < listArray.get(i).toCharArray().length; j++) {
                res[i][j] = String.valueOf(listArray.get(i).toCharArray()[j]);
            }
        }
        return res;
    }
}
