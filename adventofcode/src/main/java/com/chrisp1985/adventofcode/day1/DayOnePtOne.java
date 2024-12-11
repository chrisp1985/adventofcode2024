package com.chrisp1985.adventofcode.day1;

import com.chrisp1985.adventofcode.libs.BaseAbstract;
import com.chrisp1985.adventofcode.libs.Utils;
import java.util.Arrays;
import java.util.List;

public class DayOnePtOne extends BaseAbstract {

    int[] l;
    int[] r;

    public void solution(String inputFilePath) {

        setSortedArrays(inputFilePath);
        System.out.println("Sum is: " + returnSizeDifferenceBetweenOrderedArrays(l, r));

    }

    protected void setSortedArrays(String inputFilePath) {
        List<String[]> foundStrings = this.utils.getListOfStrings(inputFilePath);
        List<int[]> foundInts = this.utils.strArrToIntArray(foundStrings);

        l = this.utils.getArrayFromList(foundInts, 0);
        r = this.utils.getArrayFromList(foundInts, 1);

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
