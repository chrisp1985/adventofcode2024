package com.chrisp1985.adventofcode.day11;

import com.chrisp1985.adventofcode.libs.BaseAbstract;

import java.util.*;
import java.util.stream.Collectors;

public class DayEleven extends BaseAbstract {

    static Long SUM = 0L;
    public void solution(String inputFilePath) {

        List<String> lines = this.utils.readLinesOfFile(inputFilePath);
        String[] stones = lines.get(0).split("\\s");

        List<String> initialList = Arrays.asList(stones);

        List<Long> longList = initialList.stream().mapToLong(Long::parseLong).boxed().toList();

        long sum = 0L;
        for(int i=0; i < longList.size(); i++) {
            sum += getStonesFromBlinksMemo(longList.get(i), new HashMap<>(), 75);
        }
        System.out.println(sum);
    }

    private Long getStonesFromBlinksMemo(Long stone, Map<String, Long> memo, int iteration) {
        String key = iteration + ":" + stone;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (iteration == 0) {
            return 1L;
        }

        if (stone == 0L) {
            long result = getStonesFromBlinksMemo(1L, memo, iteration-1);
            memo.put(key, result);
            return result;
        }
        else if (String.valueOf(stone).length() % 2 == 0) {
            final long mid = String.valueOf(stone).length() / 2;
            long right = Long.parseLong(String.valueOf(stone).substring(0, (int) mid));
            long left = Long.parseLong(String.valueOf(stone).substring((int) mid));

            long result = getStonesFromBlinksMemo(left, memo, iteration - 1) + getStonesFromBlinksMemo(right, memo, iteration - 1) ;
            memo.put(key, result);
            return result;
        }

        long result = getStonesFromBlinksMemo(stone * 2024L, memo, iteration-1);
        memo.put(key, result);
        return result;
    }
}
