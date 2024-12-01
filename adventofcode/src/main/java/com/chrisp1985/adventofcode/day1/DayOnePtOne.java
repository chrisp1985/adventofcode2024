package com.chrisp1985.adventofcode.day1;

import com.chrisp1985.adventofcode.libs.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayOnePtOne {

    Utils utils;

    int[] l;
    int[] r;

    public DayOnePtOne() {
        this.utils = new Utils();
    }

    public void solution(String inputFilePath) {

        setSortedArrays(inputFilePath);
        System.out.println("Sum is: " + returnSizeDifferenceBetweenOrderedArrays(l, r));

    }

    protected void setSortedArrays(String inputFilePath) {
        List<String[]> foundStrings = getListOfStrings(inputFilePath);
        List<int[]> foundInts = strArrToIntArray(foundStrings);

        l = getArrayFromList(foundInts, 0);
        r = getArrayFromList(foundInts, 1);

        Arrays.sort(l);
        Arrays.sort(r);
    }

    protected Integer returnSizeDifferenceBetweenOrderedArrays(int[] l, int[] r) {

        int sum = 0;

        for(int i =0; i < l.length; i++) {

            int difference = Math.max(r[i], l[i]) - Math.min(l[i], r[i]);
            sum += difference;

        }

        return sum;

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

            int[] intArr = { Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]) };
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
