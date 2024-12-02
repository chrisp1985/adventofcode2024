package com.chrisp1985.adventofcode.day1;

import com.chrisp1985.adventofcode.libs.BaseAbstract;
import com.chrisp1985.adventofcode.libs.Utils;
import java.util.Arrays;
import java.util.List;

public class DayOnePtOne extends BaseAbstract {

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
}
