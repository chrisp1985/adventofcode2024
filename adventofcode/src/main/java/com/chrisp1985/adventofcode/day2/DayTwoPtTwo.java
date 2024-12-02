package com.chrisp1985.adventofcode.day2;

import java.util.List;

public class DayTwoPtTwo extends DayTwoPtOne {


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

    protected boolean isArraySafe(int[] ints) {
        if(ints[0] > ints[ints.length-1]) {
            return areAllUniformWithTolerance(ints, Rate.DECREASING);
        }
        else {
            return areAllUniformWithTolerance(ints, Rate.INCREASING);
        }
    }

    public boolean areAllUniformWithTolerance(int[] ints, Rate rate) {
        if(areAllUniform(ints, rate)) {
            return true;
        }

        for(int i = 0; i < ints.length-1; i++) {
            switch (rate) {
                case INCREASING -> {
                    if(ints[i+1] - ints[i] > 3 || ints[i+1] - ints[i] < 1) {
                        if(areAllUniform(createNewArrayWithElementRemoved(ints, i), Rate.INCREASING) ||
                                areAllUniform(createNewArrayWithElementRemoved(ints, i+1), Rate.INCREASING)) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
                case DECREASING -> {
                    if(ints[i] - ints[i+1] > 3 || ints[i] - ints[i+1] < 1) {
                        if(areAllUniform(createNewArrayWithElementRemoved(ints, i), Rate.DECREASING) ||
                                areAllUniform(createNewArrayWithElementRemoved(ints, i+1), Rate.DECREASING)) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private int[] createNewArrayWithElementRemoved(int[] ints, int index) {

        int count = 0;
        int[] array = new int[ints.length-1];
        for(int i=0; i < ints.length; i++) {
            if(i!=index) {
                array[count] = ints[i];
                count++;
            }
        }
        return array;
    }

}
