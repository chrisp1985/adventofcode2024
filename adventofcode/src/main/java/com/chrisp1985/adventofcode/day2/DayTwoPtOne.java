package com.chrisp1985.adventofcode.day2;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.List;

public class DayTwoPtOne extends BaseAbstract {


    public void solution(String inputFilePath) {
        List<int[]> foundInts = strArrToIntArray(getListOfStrings(inputFilePath));
        int count = 0;
        for(int i = 0; i < foundInts.size(); i++) {
            if(isArraySafe(foundInts.get(i))) {
                count++;
            }
        }
        System.out.println(count);
    }

    private boolean isArraySafe(int[] ints) {
        if(ints[0] > ints[ints.length-1]) {
            return areAllUniform(ints, Rate.DECREASING);
        }
        else {
            return areAllUniform(ints, Rate.INCREASING);
        }
    }

    protected boolean areAllUniform(int[] ints, Rate rate) {
        for(int i =0; i < ints.length-1; i++) {

            switch (rate) {
                case DECREASING -> {
                    if(ints[i] - ints[i+1] > 3 || ints[i] - ints[i+1] < 1) {

                        return false;

                    }
                }
                case INCREASING -> {
                    if(ints[i+1] - ints[i] > 3 || ints[i+1] - ints[i] < 1) {

                        return false;
                    }
                }
            }
        }
        return true;
    }

    public enum Rate {
        INCREASING,
        DECREASING
    }
}
