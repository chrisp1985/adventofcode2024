package com.chrisp1985.adventofcode.day15;

import com.chrisp1985.adventofcode.libs.ArrayWrapper;
import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.*;

public class DayFifteen extends BaseAbstract {

    public void solutionPt1(String inputFilePath) {
        String[][] grid = createGrid(getGridContents(inputFilePath));
        List<Character> instructions = getInstructions(inputFilePath);
        movePt1(grid, instructions, findInitialPosition(grid));

        int sum = 0;
        for(int[] lydia : findAllBoxes(grid)) {
            sum+= 100* lydia[0] + lydia[1];
        }

        System.out.println(sum);
    }

    public void solution(String inputFilePath) {
        String[][] grid = createBiggerGrid(getGridContents(inputFilePath));
        List<Character> instructions = getInstructions(inputFilePath);
        movePt2(grid, instructions, findInitialPosition(grid));

        printGrid(grid);

        int sum = 0;
        for(int[] lydia : findAllBoxEdges(grid)) {
            sum+= 100* lydia[0] + lydia[1];
        }
        System.out.println(sum);
    }

    private int[] getNextStep(Character step, int i, int j) {

        if(step == '<') {
            return new int[] { i, j-1};
        }
        else if(step == '^') {
            return new int[] { i-1, j};
        }
        else if(step == '>') {
            return new int[] { i, j+1};
        }
        else if(step == 'v') {
            return new int[] { i+1, j};
        }

        return new int[] {0, 0};

    }

    List<int[]> findAllBoxes(String[][] grid) {

        List<int[]> res = new ArrayList<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].equals("O")) {
                    res.add(new int[] { i, j});
                }
            }
        }

        return res;
    }

    List<int[]> findAllBoxEdges(String[][] grid) {

        List<int[]> res = new ArrayList<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].equals("[")) {
                    res.add(new int[] { i, j});
                }
            }
        }

        return res;
    }

    private void movePt2(String[][] grid, List<Character> instructions, int[] startingPosition) {

        for(Character move : instructions) {
            boolean hitHash = false;
            Stack<int[]> stack = new Stack<>();
            Queue<int[]> localstack = new ArrayDeque<>();
            int[] next = getNextStep(move, startingPosition[0], startingPosition[1]);

            if(grid[next[0]][next[1]].equals("]") || grid[next[0]][next[1]].equals("[")) {
                if(move == '^' || move == 'v') {
                    // If next is a box side, add it and other to localstack.
                    if(grid[next[0]][next[1]].equals("]") || grid[next[0]][next[1]].equals("[")) {
                        localstack.add(next);
                        int[] otherSide = grid[next[0]][next[1]].equals("]") ?
                                new int[] {next[0], next[1] -1} : new int[] {next[0], next[1] +1};
                        localstack.add(otherSide);

                        while(!localstack.isEmpty()) {
                            int[] current = localstack.remove();
                            int[] nextCurrent = getNextStep(move, current[0], current[1]);
                            stack.add(current);

                            if(grid[nextCurrent[0]][nextCurrent[1]].equals("#")) {
                                stack.clear();
                                hitHash = true;
                                break;
                            }
                            else if(grid[nextCurrent[0]][nextCurrent[1]].equals(".")) {
                                //stack.add(current);
                            }
                            else {
                                localstack.add(nextCurrent);
                                otherSide = grid[nextCurrent[0]][nextCurrent[1]].equals("]") ?
                                        new int[] {nextCurrent[0], nextCurrent[1] -1} : new int[] {nextCurrent[0], nextCurrent[1] +1};
                                localstack.add(otherSide);

                            }
                        }

                    }
                }
                else {
                    try {
                        while(!grid[next[0]][next[1]].equals(".")) {
                            if(grid[next[0]][next[1]].equals("#")) {
                                stack.clear();
                                break;
                            }
                            if (grid[next[0]][next[1]].equals("]") || grid[next[0]][next[1]].equals("[")) {
                                stack.add(next);
                                next = getNextStep(move, next[0], next[1]);

                                //TODO: THIS DONT WORK GOOD.
                            }
                        }

                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        printGrid(grid);
                        System.out.println();
                    }
                }
            }

            if(grid[next[0]][next[1]].equals("#")) {
                hitHash = true;
            }

            Set<ArrayWrapper> coordsFound = new HashSet<>();
            while(!stack.isEmpty()) {

                int[] found = stack.pop();
                if(!coordsFound.contains(new ArrayWrapper(found))) {
                    int[] nextBox = getNextStep(move, found[0], found[1]);
                    grid[nextBox[0]][nextBox[1]] = grid[found[0]][found[1]];
                    grid[found[0]][found[1]] = ".";

                    coordsFound.add(new ArrayWrapper(found));
                }

            }

            if(!hitHash) {
                grid[startingPosition[0]][startingPosition[1]] = ".";
                startingPosition = getNextStep(move, startingPosition[0], startingPosition[1]);
                grid[startingPosition[0]][startingPosition[1]] = "@";
            }

//            System.out.println(move);
//            printGrid(grid);
        }
    }

    private void movePt1(String[][] grid, List<Character> instructions, int[] startingPosition) {

        for(Character move : instructions) {
            Stack<int[]> stack = new Stack<>();
            int[] next = getNextStep(move, startingPosition[0], startingPosition[1]);

            while(grid[next[0]][next[1]].equals("O")) {
                stack.add(next);
                next = getNextStep(move, next[0], next[1]);
            }

            if(!grid[next[0]][next[1]].equals("#")) {
                while(!stack.isEmpty()) {

                    int[] found = stack.pop();
                    int[] nextBox = getNextStep(move, found[0], found[1]);
                    grid[nextBox[0]][nextBox[1]] = grid[found[0]][found[1]];

                }

                grid[startingPosition[0]][startingPosition[1]] = ".";
                startingPosition = getNextStep(move, startingPosition[0], startingPosition[1]);
                grid[startingPosition[0]][startingPosition[1]] = "@";

            }
        }
    }

    protected int[] findInitialPosition(String[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].equals("@")) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
    }

    List<String> getGridContents(String inputFilePath) {
        List<String> lines = this.utils.readLinesOfFile(inputFilePath);
        List<String> res = new ArrayList<>();

        for(String line : lines) {
            if(line.startsWith("#")) {
                res.add(line);
            }
        }
        return res;
    }

    String[][] createGrid(List<String> gridContents) {
        return this.utils.create2dArrayFromStringList(gridContents);
    }

    String[][] createBiggerGrid(List<String> gridContents) {
        String[][] initGrid = this.utils.create2dArrayFromStringList(gridContents);
        String[][] biggerGrid = new String[initGrid.length][initGrid[0].length * 2];

        for(int i = 0 ; i < initGrid.length; i++) {
            for(int j = 0; j < initGrid[i].length; j++) {
                if(initGrid[i][j].equals(".")) {
                    biggerGrid[i][j*2] = ".";
                    biggerGrid[i][j*2+1] = ".";
                }
                else if(initGrid[i][j].equals("#")) {
                    biggerGrid[i][j*2] = "#";
                    biggerGrid[i][j*2+1] = "#";
                }
                else if(initGrid[i][j].equals("O")) {
                    biggerGrid[i][j*2] = "[";
                    biggerGrid[i][j*2+1] = "]";
                }
                else if(initGrid[i][j].equals("@")) {
                    biggerGrid[i][j*2] = "@";
                    biggerGrid[i][j*2+1] = ".";
                }
            }
        }

        return biggerGrid;

    }

    List<Character> getInstructions(String inputFilePath) {
        List<String> lines = this.utils.readLinesOfFile(inputFilePath);

        List<Character> res = new ArrayList<>();

        for(String line : lines) {
            if(line.contains("<")) {
                for(Character character : line.toCharArray()) {
                    res.add(character);
                }
            }
        }
        return res;
    }

    private void printGrid(String [][] gridToPrint) {
        for(int i = 0; i < gridToPrint.length; i++) {
            for (int j = 0; j < gridToPrint[i].length; j++) {
                System.out.printf(gridToPrint[i][j]);
            }
            System.out.print("\n");
        }
    }
}
