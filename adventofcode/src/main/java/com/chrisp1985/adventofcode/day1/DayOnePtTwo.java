package com.chrisp1985.adventofcode.day1;

import java.util.HashMap;

public class DayOnePtTwo extends DayOnePtOne {

    public void solution(String inputFilePath) {
        setSortedArrays(inputFilePath);
        HashMap<Integer, Integer> mapOfEntries = getMapOfCountsForEachNumber();

        int sum = 0;
        for(int i =0; i < l.length; i++) {

            int additional = 0;

            if(mapOfEntries.get(l[i]) != null) {

                additional = l[i] * mapOfEntries.get(l[i]);

            }

            sum += additional;
        }

        System.out.println(sum);
    }

    private HashMap<Integer, Integer> getMapOfCountsForEachNumber() {
        HashMap<Integer, Integer> mapOfEntries = new HashMap<>();

        for(int i =0; i < r.length; i++) {

            if(mapOfEntries.get(r[i]) == null) {
                mapOfEntries.put(r[i], 1);
            }
            else {
                mapOfEntries.put(r[i], mapOfEntries.get(r[i])+1);
            }

        }

        return mapOfEntries;
    }
}
