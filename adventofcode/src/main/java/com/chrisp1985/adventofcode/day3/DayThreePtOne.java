package com.chrisp1985.adventofcode.day3;

import com.chrisp1985.adventofcode.libs.BaseAbstract;
import com.chrisp1985.adventofcode.libs.Utils;

import java.util.ArrayList;
import java.util.regex.*;

import java.util.List;

public class DayThreePtOne extends BaseAbstract {
    protected static Integer totalSum;
    private static final String MULTIVALUE_REGEX = "mul\\(\\d+,\\d+\\)";
    private static final String INT_REGEX = "\\d+";

    public void solution(String inputFilePath) {
        List<String> lines = this.utils.readLinesOfFile(inputFilePath);
        totalSum = 0;

        List<String> matches = getMatchesFromRegex(MULTIVALUE_REGEX, lines);
        getValuesAndMultiply(matches);
    }

    protected List<String> getMatchesFromRegex(String regex, List<String> inputText) {
        Pattern pattern = Pattern.compile(regex);
        List<String> matches = new ArrayList<>();

        for(String line : inputText) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                matches.add(matcher.group());
            }
        }
        return matches;
    }

    protected void getValuesAndMultiply(List<String> matches) {
        Pattern pattern = Pattern.compile(INT_REGEX);
        for(String match : matches) {
            Matcher matcher = pattern.matcher(match);

            matcher.find();
            int firstVal = Integer.valueOf(matcher.group());

            matcher.find();
            int secondVal = Integer.valueOf(matcher.group());

            int multiplication = firstVal * secondVal;

            totalSum += multiplication;
            System.out.println("CUMULATIVE SUM: " + totalSum);
        }
    }
}
