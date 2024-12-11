package com.chrisp1985.adventofcode.day7;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySeven extends BaseAbstract {

    List<Long> foundValues;
    public void solution(String inputFilePath) {
        List<String> lines = this.utils.readLinesOfFile(inputFilePath);
        foundValues = new ArrayList<>();
        long sum = 0;

        for(String line : lines) {
            String[] splitLine = line.split("\\:");
            long numberToLookFor = Long.parseLong(splitLine[0]);
            String[] numsToFindStrs = splitLine[1].trim().split("\\s");
            long[] numsArr = new long[numsToFindStrs.length];
            for(String ignored : numsToFindStrs) {
                for(int i = 0; i < numsToFindStrs.length; i++) {
                    numsArr[i] = Long.parseLong(numsToFindStrs[i]);
                }
            }
            if(isMakeable(numsArr, 0L, numberToLookFor)) {
                sum+=numberToLookFor;
            }

        }
        System.out.println(sum);
    }

    private boolean isMakeable(long[] nums, Long sum, long target) {

        if(nums.length == 0) {
            return sum == target;
        }

        long multiSum = sum * nums[0];
        long addSum = sum + nums[0];
        long[] stringMesh = addFirstElementsTogether(nums, sum);
        long stringMashSum = stringMesh[0];

        return
                isMakeable(removeFirstElement(stringMesh), stringMashSum, target) ||
                isMakeable(removeFirstElement(nums), multiSum, target) ||
                isMakeable(removeFirstElement(nums), addSum, target);
    }

    protected long[] addFirstElementsTogether(long[] array, Long sum) {

        long[] newArr;
        if(array.length < 2) {
            return new long[] { Long.parseLong(sum + "" + array[0]) };
        }
        if(sum==0) {
            newArr = new long[array.length-1];
            newArr[0] = Long.parseLong(array[0] + "" + array[1]);
            int count = 1;
            for(int i=2; i < array.length; i++) {
                newArr[count] = array[i];
                count++;
            }
        }
        else {
            newArr = Arrays.copyOf(array, array.length);
            newArr[0] = Long.parseLong(sum + "" + array[0]);
        }

        return newArr;
    }

    protected long[] removeFirstElement(long[] array) {

        long[] newArr = new long[array.length-1];
        int count = 0;
        for(int i = 0; i < array.length; i++) {
            if(i != 0) {
                newArr[count] = array[i];
                count++;
            }
        }
        return newArr;
    }

}
