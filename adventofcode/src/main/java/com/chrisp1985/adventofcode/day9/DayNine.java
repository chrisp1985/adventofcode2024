package com.chrisp1985.adventofcode.day9;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.*;

public class DayNine extends BaseAbstract {


    public void solution(String inputFilePath) {

        List<String> inputLine = this.utils.stringToStringList(this.utils.readSingleLineOfFile(inputFilePath, 0));
        int[] intArr = this.utils.stringToIntArray(this.utils.readSingleLineOfFile(inputFilePath, 0));
        List<String> arrangedLineList = getListOfArrangedBlocks(inputLine);

        List<Long> arrangedBlocks = getListOfArrangedBlocksPt2(intArr);


        List<String> reOrganisedListOfStrings = reOrganiseList(arrangedLineList);
        long pt1sum = 0;
        for(int position = 0; position < reOrganisedListOfStrings.size(); position++) {
            long fileId = Long.parseLong(String.valueOf(reOrganisedListOfStrings.get(position)));
            long localSum = position * fileId;
            pt1sum += localSum;
        }
        System.out.println(pt1sum);

        Long sum = arrangedBlocks.stream().mapToLong(Long::longValue).sum();
        System.out.println(sum);
    }

    private List<Long> getListOfArrangedBlocksPt2(int[] initialArray) {
        List<Long> sums = new ArrayList<>();

        // Create an array of the gaps.
        int[] gapsArray = new int[initialArray.length];

        for(int i = 1; i < gapsArray.length; i++) {
            gapsArray[i] = gapsArray[i-1] + initialArray[i-1];
        }

        // From back to front, can I fit in a gap?
        for(int r = initialArray.length -1; r >= 0; r-=2) {

            boolean found = false;
            for(int l = 1; l < r; l+=2) {

                // If the right value can fit in the gap value on the left.
                if(initialArray[r] <= initialArray[l]) {

                    // Do the sums for all the ones on the right.
                    for(int i = 0; i < initialArray[r]; i++) {

                        sums.add((long) (r /2) * (gapsArray[l] +i));

                    }

                    // Decrease the count on the gaps by the number of entries added.
                    initialArray[l] -= initialArray[r];

                    // Set the gapsArray to include the new values from the right and keep track of where we are.
                    gapsArray[l] += initialArray[r];
                    found = true;
                    break;

                }

            }

            if(!found) {
                for (int i = 0; i < initialArray[r]; i++) {
                    sums.add((long) (r / 2) * (gapsArray[r] + i));
                }
            }

        }

        return sums;

    }

    private List<String> reOrganiseList(List<String> splitArrangedLine) {
        List<String> res = new ArrayList<>();
        int l = 0;
        int r = splitArrangedLine.size()-1;

        while(l <= r) {
            if(splitArrangedLine.get(l).equals(".")) {
                if(!splitArrangedLine.get(r).equals(".")) {
                    res.add(splitArrangedLine.get(r));
                    r--;
                    l++;
                }
                else {
                    r--;
                }
            }
            else {
                res.add(splitArrangedLine.get(l));
                l++;
            }
        }

        return res;
    }

    private List<String> getListOfArrangedBlocks(List<String> inputLine) {
        List<String> res = new ArrayList<>();
        for(int i=0; i < inputLine.size(); i++) {
            if(i%2==0) {
                for(int j = 0; j < Integer.parseInt(inputLine.get(i)); j++) {
                    res.add(String.valueOf(i / 2));
                }
            }
            else {
                for(int j = 0; j < Integer.parseInt(inputLine.get(i)); j++) {
                    res.add(".");
                }
            }
        }
        return res;
    }
}
