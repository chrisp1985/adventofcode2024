package com.chrisp1985.adventofcode.day4;

import java.util.HashMap;
import java.util.Map;

public class DayFourPtTwo extends DayFourPtOne {


    public void solution(String inputFilePath) {
        int count = 0;
        this.grid = this.utils.create2dArrayFromStringListArray(this.utils.getListOfStrings(inputFilePath));

        for(int i=0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if(isCrossMas(i, j)) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }


    protected boolean isCrossMas(int i, int j) {

        HashMap<int[], int[]> map = new HashMap<>() {{
           put(new int[]{i - 1, j - 1}, new int[]{i + 1, j + 1});
           put(new int[]{i - 1, j + 1}, new int[]{i + 1, j - 1});
        }};

        int correctCount = 0;
        if (grid[i][j].charAt(0) == 'A') {
            for(Map.Entry<int[], int[]> entry : map.entrySet()) {
                if (isValidGridRef(entry.getKey()) && isValidGridRef(entry.getValue())) {
                    if(grid[entry.getKey()[0]][entry.getKey()[1]].charAt(0) == 'S' && grid[entry.getValue()[0]][entry.getValue()[1]].charAt(0) == 'M'
                            || grid[entry.getKey()[0]][entry.getKey()[1]].charAt(0) == 'M' && grid[entry.getValue()[0]][entry.getValue()[1]].charAt(0) == 'S') {

                        correctCount++;

                    }

                }
            }
        }

        return correctCount == 2;
    }
}
