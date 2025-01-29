package com.chrisp1985.adventofcode.day12;

import com.chrisp1985.adventofcode.libs.ArrayWrapper;
import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.*;

public class DayTwelve extends BaseAbstract {

    String[][] grid;
    static Integer SIDECOUNT =0;

    static Set<ArrayWrapper> globalCache = new HashSet<>();

    static Integer cornersFound = 0;

    public void solution(String inputFilePath) {
        int partA_sum = 0;
        int partB_sum = 0;
        this.grid = this.utils.create2dArrayFromStringListArray(this.utils.getListOfStrings(inputFilePath));
        for(int i = 0 ; i < this.grid.length; i++) {
            for(int j = 0 ; j < this.grid[i].length; j++) {
                ArrayWrapper coord = new ArrayWrapper(new int[] { i , j});
                Set<ArrayWrapper> cache = new HashSet<>();
                if(!globalCache.contains(coord)) {
                    getAllTiles(i, j, this.grid[i][j], cache);
                    globalCache.addAll(cache);
                    partA_sum+= cache.size() * SIDECOUNT;
                    SIDECOUNT = 0;
                }

                Iterator<ArrayWrapper> iterator = cache.iterator();

                while(iterator.hasNext()){
                    int[] arr = iterator.next().getArray();
                    cornersFound+= hasCorners(arr[0],
                            arr[1],
                            grid[arr[0]][arr[1]]);
                }

                partB_sum += cache.size() * cornersFound;

                cornersFound = 0;

            }
        }

        System.out.println(partA_sum);
        System.out.println(partB_sum);

    }

    private Integer hasCorners(int i, int j, String patternToFind) {
        int count = 0;

        // is an inny corner
        // eg up and left OK, but diagonal isn't.
        if((i-1 > -1 && grid[i-1][j].equals(patternToFind))
                && (j+1 < grid[0].length && grid[i][j+1].equals(patternToFind))
                && !grid[i-1][j+1].equals(patternToFind)) { // up and right
            count++;
        }
        if((j+1 < grid[0].length && grid[i][j+1].equals(patternToFind))
                && (i+1 < grid.length && grid[i+1][j].equals(patternToFind))
                && !grid[i+1][j+1].equals(patternToFind)) { // right and down
            count++;
        }
        if((i+1 < grid.length && grid[i+1][j].equals(patternToFind))
                && (j-1 > -1 && grid[i][j-1].equals(patternToFind))
                && !grid[i+1][j-1].equals(patternToFind)) { // down and left
            count++;
        }
        if((j-1 > -1 && grid[i][j-1].equals(patternToFind))
                && (i-1 > -1 && grid[i-1][j].equals(patternToFind))
                && !grid[i-1][j-1].equals(patternToFind)) { // left and up
            count++;
        }

        // is an outty corner.
        // eg up, right and diagonal aren't.
        if((i-1 < 0 || !grid[i-1][j].equals(patternToFind))
                && (j+1 == grid[i].length || !grid[i][j+1].equals(patternToFind))) { // up and right
            count++;
        }
        if((j+1 == grid[1].length || !grid[i][j+1].equals(patternToFind))
                && (i+1 == grid.length || !grid[i+1][j].equals(patternToFind))) { // right and down
            count++;
        }
        if((i+1 == grid.length || !grid[i+1][j].equals(patternToFind))
                && (j-1 < 0 || !grid[i][j-1].equals(patternToFind))) { // down and left
            count++;
        }
        if((j-1 < 0 || !grid[i][j-1].equals(patternToFind))
                && (i-1 < 0 || !grid[i-1][j].equals(patternToFind))) { // left and up
            count++;
        }

        return count;
    }

    private Set<ArrayWrapper> getAllTiles(int i, int j, String patternToFind, Set<ArrayWrapper> cache) {

        if(cache.contains(new ArrayWrapper(new int[] { i , j}))) {
            return cache;
        }
        if(i < 0 || j < 0 || i >= grid.length  || j >= grid[i].length) {
            SIDECOUNT++;
            return cache;
        }
        if(!this.grid[i][j].equals(patternToFind)) {
            SIDECOUNT++;
            return cache;
        }
        else {
            int[][] availableOptions = new int[][]{
                    new int[]{i + -1, j},
                    new int[]{i, j + 1},
                    new int[]{i + 1, j},
                    new int[]{i, j + -1}};
            for(int[] coord : availableOptions) {
                cache.add(new ArrayWrapper(new int[] { i, j }));
                getAllTiles(coord[0], coord[1], patternToFind, cache);
            }
        }

        return cache;

    }
}
