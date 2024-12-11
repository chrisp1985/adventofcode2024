package com.chrisp1985.adventofcode.day4;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

public class DayFourPtOne extends BaseAbstract {

    String[][] grid;
    static int globalCount = 0;

    public void solution(String inputFilePath) {
        this.grid = this.utils.create2dArrayFromStringListArray(this.utils.getListOfStrings(inputFilePath));

        for(int i=0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                char[] xMas = "XMAS".toCharArray();
                if(i == 0 && j == 18) {

                    System.out.println();
                }
                int[][] elementsSurrounding = {
                        {i, j-1},
                        {i-1, j-1},
                        {i-1, j},
                        {i-1, j+1},
                        {i, j+1},
                        {i+1, j+1},
                        {i+1, j},
                        {i+1, j-1}
                };

                int[][] baseSurrounding = {
                        {0, -1},
                        {-1, -1},
                        {-1, 0},
                        {-1, 1},
                        {0, 1},
                        {1, 1},
                        {1, 0},
                        {1, -1}
                };
                nearElement(elementsSurrounding, baseSurrounding, xMas, i, j);
            }
        }

        System.out.println("COUNT: " + globalCount);
    }

    protected boolean nearElement(int[][] elementsSurrounding, int[][] baseSurrounding, char[] xMas, int i, int j) {

        if (grid[i][j].charAt(0) == xMas[0]) {
            if(xMas.length == 1) {
                globalCount++;
                return true;
            }
            for(int k = 0; k < elementsSurrounding.length; k++) {
                if (isValidGridRef(elementsSurrounding[k])) {
                    int[][] newElementsSurrounding = new int[][] { { elementsSurrounding[k][0] + baseSurrounding[k][0], elementsSurrounding[k][1] + baseSurrounding[k][1] }};
                    char[] arrayRemoved = removeFirstElement(xMas);
                    nearElement(newElementsSurrounding, new int[][] { baseSurrounding[k]}, arrayRemoved, i+baseSurrounding[k][0], j+baseSurrounding[k][1]);
                }
            }
        }

        return false;
    }

    protected boolean isValidGridRef(int[] element) {
        return element[0] > -1 && element[0] < grid.length && element[1] > -1 && element[1] < grid[0].length;
    }

    protected char[] removeFirstElement(char[] array) {

        char[] newArr = new char[array.length-1];
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
