package com.chrisp1985.adventofcode.day8;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.*;

public class DayEight extends BaseAbstract {

    String[][] grid;
    public void solution(String inputFilePath) {

        this.grid = this.utils.create2dArrayFromStringListArray(this.utils.getListOfStrings(inputFilePath));
        Map<String, List<int[]>> antennas = getAllAntennas();
        int count = addHashToGridForAntinodes(antennas);

        System.out.println(count);
    }

    private void printGrid(String [][] gridToPrint) {
        for(int i = 0; i < gridToPrint.length; i++) {
            for (int j = 0; j < gridToPrint[i].length; j++) {
                System.out.printf(gridToPrint[i][j]);
            }
            System.out.print("\n");
        }
    }

    private Map<String, List<int[]>> getAllAntennas() {
        Map<String, List<int[]>> antennasFound = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!grid[i][j].equals(".")) {
                    if (antennasFound.get(grid[i][j]) == null) {
                        antennasFound.put(grid[i][j], List.of(new int[]{i, j}));
                    } else {
                        List<int[]> antennaCoords = new ArrayList<>(antennasFound.get((grid[i][j])));
                        antennaCoords.add(new int[]{i, j});
                        antennasFound.put(grid[i][j], antennaCoords);
                    }
                }
            }
        }
        return antennasFound;
    }

    private Integer addHashToGridForAntinodes(Map<String, List<int[]>> antennas) {

        Set<List<Integer>> entries = new HashSet<>();

        for(Map.Entry<String, List<int[]>> entry : antennas.entrySet()) {

            List<int[]> coordsForEntry = entry.getValue();
            for (int i = 0; i < coordsForEntry.size(); i++) {
                for (int j = 0; j < coordsForEntry.size(); j++) {
                    entries.add(Arrays.stream(new int[] { coordsForEntry.get(i)[0] , coordsForEntry.get(i)[1]}).boxed().toList()); // PART 2 ONLY
                    int i_diff = coordsForEntry.get(i)[0] - coordsForEntry.get(j)[0];
                    int j_diff = coordsForEntry.get(i)[1] - coordsForEntry.get(j)[1];
                    if(coordsForEntry.get(i)[0] != coordsForEntry.get(j)[0] && coordsForEntry.get(i)[1] != coordsForEntry.get(j)[1]) {
                        int newI = coordsForEntry.get(i)[0] + i_diff;
                        int newJ = coordsForEntry.get(i)[1] + j_diff;

                        while(newI > -1 // WHILE IS PART 2 ONLY, OTHERWISE PART 1.
                                && newJ > -1
                                && newI < grid.length
                                && newJ < grid[coordsForEntry.get(i)[0]].length
                        ) {
                            entries.add(Arrays.stream(new int[] { newI , newJ}).boxed().toList());
                            if(grid[newI][newJ].equals(".")) {
                                grid[newI][newJ] = "#";
                            }

                            newI = newI + i_diff;
                            newJ = newJ + j_diff;
                        }
                    }
                }
            }
        }
        return entries.size();
    }
}
