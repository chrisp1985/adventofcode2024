package com.chrisp1985.adventofcode.libs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseAbstract {

    Utils utils;

    public BaseAbstract() {
        utils = new Utils();
    }

    protected int[] getArrayFromList(List<int[]> ints, int index) {

        int[] res = new int[ints.size()];

        for(int i = 0; i < ints.size(); i++) {
            res[i] = ints.get(i)[index];
        }

        return res;
    }


    protected List<int[]> strArrToIntArray(List<String[]> stringArray) {

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

    protected List<String[]> getListOfStrings(String inputFilePath) {

        List<String> inputLines = utils.readLinesOfFile(inputFilePath);
        return inputLines
                .stream()
                .map(a -> a.split("\\s+"))
                .collect(Collectors.toList());
    }
}
