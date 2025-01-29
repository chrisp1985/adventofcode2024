package com.chrisp1985.adventofcode.day14;

import com.chrisp1985.adventofcode.libs.ArrayWrapper;
import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFourteen extends BaseAbstract {

    String[][] grid;

    static final int steps = 100;
    static final int width = 101;
    static final int height = 103;
    List<ArrayWrapper> parsedInput;

    public void solution(String inputFilePath) {
        parsedInput = parseInput(inputFilePath);

        for(int i = 0; i < 10000; i++) {
            List<ArrayWrapper> newCoords = new ArrayList<>();
            for(ArrayWrapper robot : parsedInput) {

                int[] robotArray = robot.getArray();
                int px = robotArray[0];
                int py = robotArray[1];
                int vx = robotArray[2];
                int vy = robotArray[3];
                int newY = Math.floorMod(py + i * vy, height);
                int newX = Math.floorMod(px + i * vx, width);

                newCoords.add(new ArrayWrapper(new int[] {newX, newY}));
            }

            if(printQuadrants(newCoords)) {
                this.grid = plotRobotsOnTreeGrid(newCoords);
                printGrid(grid);
            }
        }


    }
    public void solutionPtOne(String inputFilePath) {
        parsedInput = parseInput(inputFilePath);
        this.grid = plotRobotsOnGrid(parsedInput);
        printGrid(grid);
        System.out.println();

        List<ArrayWrapper> newCoords = new ArrayList<>();

        for(ArrayWrapper robot : parsedInput) {

            int[] robotArray = robot.getArray();
            int px = robotArray[0];
            int py = robotArray[1];
            int vx = robotArray[2];
            int vy = robotArray[3];
            int newY = Math.floorMod(py + steps * vy, height);
            int newX = Math.floorMod(px + steps * vx, width);

            newCoords.add(new ArrayWrapper(new int[] {newY, newX}));
        }

        printQuadrants(newCoords);
    }



    private boolean printQuadrants(List<ArrayWrapper> newCoords) {

        int quad1 = 0;
        int quad2 = 0;
        int quad3 = 0;
        int quad4 = 0;


        for(ArrayWrapper array : newCoords) {
            if(array.getArray()[1] < 51 && array.getArray()[0] < 50) {
                quad1++;
            }
            if(array.getArray()[1] < 51 && array.getArray()[0] > 50) {
                quad2++;
            }
            if(array.getArray()[1] > 51 && array.getArray()[0] < 50) {
                quad3++;
            }
            if(array.getArray()[1] > 51 && array.getArray()[0] > 50) {
                quad4++;
            }

        }

        System.out.printf("Quad 1: %d %n Quad 2: %d %n Quad 3: %d %n Quad 4: %d %n", quad1, quad2, quad3, quad4);

        System.out.println("Sum is: " + quad1 * quad2 * quad3 * quad4);

        if(quad1 > 300 || quad2 > 300 || quad3 > 300 || quad4 > 300) {
            System.out.println("QUAD GREATER THAN 300.");
            return true;
        }

        return false;

    }

    private List<ArrayWrapper> parseInput(String inputFilePath) {

        List<ArrayWrapper> res = new ArrayList<>();

        List<String> lines = this.utils.readLinesOfFile(inputFilePath);
        String REGEX = "-?\\d+";
        Pattern pattern = Pattern.compile(REGEX);

        for(String line : lines) {
            Matcher matcher = pattern.matcher(line);
            int[] initialCoords = new int[4];
            int count = 0;
            while (matcher.find()) {
                initialCoords[count] = Integer.parseInt(matcher.group(0));
                count++;
            }

            res.add(new ArrayWrapper(initialCoords));

        }

        return res;
    }

    // ##################################
    // ###### VISUAL EXTRAS #############
    // ##################################

    private String[][] plotRobotsOnTreeGrid(List<ArrayWrapper> coords) {
        grid = new String[height][width];
        for (int y = 0; y < grid.length; y++) {
            Arrays.fill(grid[y], ".");
        }

        for(ArrayWrapper wrapper : coords) {
            grid[wrapper.getArray()[1]][wrapper.getArray()[0]] = "#";
        }

        return grid;

    }

    private String[][] plotRobotsOnGrid(List<ArrayWrapper> parsedInput) {
        grid = new String[height][width];
        for (int y = 0; y < grid.length; y++) {
            Arrays.fill(grid[y], ".");
        }

        for(ArrayWrapper wrapper : parsedInput) {
            if(grid[wrapper.getArray()[1]][wrapper.getArray()[0]].equals(".")) {
                grid[wrapper.getArray()[1]][wrapper.getArray()[0]] = "1";
            }
            else {
                int newInt = Integer.parseInt(grid[wrapper.getArray()[1]][wrapper.getArray()[0]]) + 1;
                grid[wrapper.getArray()[1]][wrapper.getArray()[0]] = String.valueOf(newInt);
            }
        }

        return grid;

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
