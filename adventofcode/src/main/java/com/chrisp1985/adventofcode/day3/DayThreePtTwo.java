package com.chrisp1985.adventofcode.day3;

import java.util.List;

public class DayThreePtTwo extends DayThreePtOne {

    private static final String DO_DONT_MUL_REGEX = "(?:don't\\(\\)|do\\(\\)|mul\\(\\d+,\\d+\\))";
    private static final String MUL_REGEX = "mul\\(\\d+,\\d+\\)";

    public void solution(String inputFilePath) {
        totalSum = 0;
        boolean add = true;

        List<String> matches = getMatchesFromRegex(DO_DONT_MUL_REGEX, this.utils.readLinesOfFile(inputFilePath));

        for(String match : matches) {
            if(match.equals("don't()")) {
                add = false;
            }
            if(match.equals("do()")) {
                add = true;
            }
            if(match.matches(MUL_REGEX)) {
                if(add) {
                    getValuesAndMultiply(List.of(match));
                }
            }
        }
    }

}
