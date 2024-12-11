package com.chrisp1985.adventofcode.day6;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.*;

public class DaySix extends BaseAbstract {

    int[] direction;

    String printSymbol = "^";

    String inputFilePath;

    static Integer LOOPCOUNT = 0;

    public DaySix() {
        this.direction = new int[] {-1, 0};
    }

    public void solution(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        String[][] grid = createGrid(inputFilePath);

        // PART 1
        List<int[]> uniquePointsVisited = move(findInitialPosition(grid), grid);

        // PART 2
        System.out.println("PART 2.");
        int[] init = findInitialPosition(grid);
        for(int[] coords : uniquePointsVisited) {
            grid = createGrid(inputFilePath);
            grid[coords[0]][coords[1]] = "#";
            move(init, grid);
        }

        System.out.println("Positions entered is " + uniquePointsVisited.size());
        System.out.println(LOOPCOUNT);

    }

    public List<int[]> move(int[] startingPosition, String[][] grid) {
        resetDirection();
        Set<Map<List<Integer>, String>> coordsSet = new HashSet<>();

        List<int[]> uniquePointsVisited = new ArrayList<>();
        int i = startingPosition[0];
        int j = startingPosition[1];

        uniquePointsVisited.add(new int[] {i, j});

        while(i > -1 && j > -1 && i < grid.length && j < grid[i].length) {

            if(!coordsSet.add(Map.of(List.of(i, j), printSymbol))) {
                LOOPCOUNT++;
                return uniquePointsVisited;
            }

            if(grid[i][j].equals("#")) {
                i -= getDirection()[0];
                j -= getDirection()[1];
                setNewDirection();
            }
            else {
                if(!isSquareVisited(i, j, uniquePointsVisited)) {
                    uniquePointsVisited.add(new int[] {i, j});
                }
            }
            int[] next = getNextPosition(i, j);
            i = next[0];
            j = next[1];
        }

        return uniquePointsVisited;
    }

    private String[][] createGrid(String inputFilePath) {

        return this.utils.create2dArrayFromStringListArray(this.utils.getListOfStrings(inputFilePath));
    }

    protected boolean isSquareVisited(int i, int j, List<int[]> visitedSquares) {

        for(int[] square : visitedSquares) {
            if(square[0] == i && square[1] == j) {
                return true;
            }
        }
        return false;

    }

    protected int[] findInitialPosition(String[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].equals("^")) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
    }

    protected int[] getNextPosition(int i, int j) {

        return new int[] {i + direction[0], j + direction[1]};
    }

    protected int[] getDirection() {
        return direction;
    }

    private void resetDirection() {
        this.direction = new int[]{-1, 0};
        setPrintSymbol("^");
    }

    protected void setNewDirection() {

        int[] ints = getDirection();
        if (ints[0] == 0 && ints[1] == -1) {
            this.direction = new int[]{-1, 0};
            setPrintSymbol("^");
        } else if (ints[0] == -1 && ints[1] == 0) {
            this.direction = new int[]{0, 1};
            setPrintSymbol(">");
        } else if (ints[0] == 0 && ints[1] == 1) {
            this.direction = new int[]{1, 0};
            setPrintSymbol("V");
        } else if (ints[0] == 1 && ints[1] == 0) {
            this.direction = new int[]{0, -1};
            setPrintSymbol("<");
        }
    }

    private void setPrintSymbol(String symbol) {
        printSymbol = symbol;
    }

    private void printGrid(String [][] gridToPrint) {
        System.out.println("--- LOOP FOUND ---");
        for(int i = 0; i < gridToPrint.length; i++) {
            for (int j = 0; j < gridToPrint[i].length; j++) {
                System.out.printf(gridToPrint[i][j]);
            }
            System.out.print("\n");
        }
    }
}
