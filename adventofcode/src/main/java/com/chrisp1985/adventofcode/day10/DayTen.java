package com.chrisp1985.adventofcode.day10;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.HashSet;
import java.util.Set;

public class DayTen extends BaseAbstract {

    static Integer SUM = 0;

    public void solution(String inputFilePath) {

        String[][] grid = this.utils.create2dArrayFromStringListArray(this.utils.getListOfStrings(inputFilePath));

        for(int i = 0; i < grid.length; i++) {
            for(int j=0; j < grid[i].length; j++) {

                if(grid[i][j].equals("0")) {
                    Set<int[]> foundTargets = new HashSet<>();
                    recurse(grid, new int[] {i, j}, 0, 1, 9, foundTargets, true);
                    SUM += foundTargets.size();
                }
            }
        }
        System.out.println(SUM);
    }

    protected void recurse(String[][] grid, int[] coords, int initial, int nextVal, int target, Set<int[]> foundTargets, boolean part2) {

        if(coords[0] > -1 && coords[1] > -1 && coords[0] < grid.length && coords[1] < grid[coords[0]].length) {

            if (initial == target && grid[coords[0]][coords[1]].equals(String.valueOf(target))) {
                if(part2) {
                    SUM++;
                }
                else {
                    foundTargets.add(coords);
                }
            }
            else if(grid[coords[0]][coords[1]].equals(String.valueOf(initial))) {
                int[][] availableOptions = new int[][]{
                        new int[]{coords[0] + -1, coords[1]},
                        new int[]{coords[0], coords[1] + 1},
                        new int[]{coords[0] + 1, coords[1]},
                        new int[]{coords[0], coords[1] + -1}};
                for (int[] option : availableOptions) {
                    recurse(grid, option, nextVal, nextVal+1, target, foundTargets, part2);
                }
            }
        }
    }
}
