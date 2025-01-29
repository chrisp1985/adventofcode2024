package com.chrisp1985.adventofcode.day16;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.*;

public class DaySixteen extends BaseAbstract {

    private static Integer MIN_POINTS = Integer.MAX_VALUE;

    public void solution(String inputFilePath) {

        String[][] grid = this.utils.create2dArrayFromStringList(this.utils.readLinesOfFile(inputFilePath));
        MIN_POINTS = findShortestPath(grid);
        System.out.println(MIN_POINTS);
        System.out.println("Tiles contributing to shortest paths:");



    }

    protected int[] findInitialPosition(String[][] grid, String character) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].equals(character)) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
    }

    private static void printGrid(String[][] gridToPrint) {
        for(int i = 0; i < gridToPrint.length; i++) {
            for (int j = 0; j < gridToPrint[i].length; j++) {
                System.out.printf(gridToPrint[i][j]);
            }
            System.out.print("\n");
        }
    }

    // ####################################
    // ######### DIJSKTRA #################
    // ####################################

    // Dijkstra finds the shortest path possible in weighted nodes. It does this by constantly evaluating the lowest value
    // node and trying to find the next possible shortest route.

    // 1. Add the starter node to the priority queue.
    // 2. While the queue isn't empty, take the node off the top (it will be the lowest cost node).
    // 3. If the value is E, we've reached the end so return the cost.
    // 4. Otherwise, if we haven't been there before, create a key for the visited hashmap so we know we've visited and what the cost of that space is.
    // 5. If we have been there, do nothing with the item popped off the queue and continue to the next one.
    // 6. For each direction for the current node, evaluate whether they're a hash.
    // 6 a. If it's a hash, ignore it. If not, add the next node to the priority queue with the cost.
    // 7. When all the neighbours are evaluated, we go back to the while to look at all of the new nodes available.
    // 8. This being a heap sorted by lowest cost, we'll pick the best looking option and go with it.

    public int findShortestPath(String[][] grid) {

        // Directions: up (0), down (1), left (2), right (3)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int[] start = findInitialPosition(grid, "S");
        int[] end = findInitialPosition(grid, "E");

        // Priority queue for Dijkstra's algorithm
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Node(start[0], start[1], 3, 0)); // Set the marker facing East.

        Map<String, Integer> visited = new HashMap<>();

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int x = current.x, y = current.y, direction = current.direction, cost = current.cost;
            if (x == end[0] && y == end[1]) {
                return cost;
            }

            String key = x + "," + y + "," + direction;

            if (visited.containsKey(key) && visited.get(key) <= cost) {
                continue;
            }
            visited.put(key, cost);

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (!grid[newX][newY].equals("#")) {
                    int newCost = cost + 1;
                    if (direction != i) { // If direction is different from the way we're currently heading...
                        newCost += 1000; // Add some more on for turning.
                    }
                    pq.add(new Node(newX, newY, i, newCost));
                }
            }
        }

        return -1;
    }

    static class Node {
        int x, y, direction, cost;

        Node(int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
    }

    static class PartTwoNode {
        int x, y, cost;

        PartTwoNode(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }


}
