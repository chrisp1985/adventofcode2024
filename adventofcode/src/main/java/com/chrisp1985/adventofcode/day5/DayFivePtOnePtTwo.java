package com.chrisp1985.adventofcode.day5;

import com.chrisp1985.adventofcode.libs.BaseAbstract;
import com.chrisp1985.adventofcode.libs.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFivePtOnePtTwo extends BaseAbstract {

    Utils utils;

    public DayFivePtOnePtTwo() {
        this.utils = new Utils();
    }

    public void solution(String inputFilePath) {
        int sumOfCorrectMiddles = 0;
        int sumOfIncorrectMiddles = 0;

        Map<Integer, List<Integer>> mappedRules = turnRulesIntoMap(groupsFromFile(inputFilePath, "(\\d+\\|\\d+)"));
        List<List<Integer>> listOfPages = turnPagesIntoList(groupsFromFile(inputFilePath, "^\\d+(,\\d+)*$"));

        for(List<Integer> pages : listOfPages) {
            if(isInCorrectOrder(pages, mappedRules)) {
//                sumOfCorrectMiddles += findMiddleValue(pages);
            }
            else {
                List<Integer> reorderPages = reorderIncorrectPages(pages, mappedRules);
                sumOfIncorrectMiddles += findMiddleValue(reorderPages);
            }
        }

        System.out.println(sumOfCorrectMiddles);
        System.out.println(sumOfIncorrectMiddles);
    }

    protected boolean isInCorrectOrder(List<Integer> pages, Map<Integer, List<Integer>> mappedRules) {
        List<Integer> pagesCovered = new ArrayList<>();
        for(Integer page : pages) {
            for(Integer prevPage : pagesCovered) {
                if(mappedRules.get(page).contains(prevPage)) {
                    return false;
                }
            }
            pagesCovered.add(page);
        }
        return true;
    }

    protected List<Integer> reorderIncorrectPages(List<Integer> pages, Map<Integer, List<Integer>> mappedRules) {
        List<Integer> res = new ArrayList<>();

        while(!pages.isEmpty()) {
            List<Integer> pagesRes= new ArrayList<>();
            for(Integer page : pages) {
                List<Integer> pagesWithCurrentPageRemoved = removeElementFromList(pages, page);
                boolean noPreviousElements = true;
                for(Integer pageFound : pagesWithCurrentPageRemoved) {
                    if(mappedRules.get(page).contains(pageFound)) {
                        noPreviousElements = false;
                    }
                }
                if(noPreviousElements) {
                    res.add(page);
                    pagesRes = pagesWithCurrentPageRemoved;
                }
            }
            pages = pagesRes;
        }

        return res;
    }

    protected List<Integer> removeElementFromList(List<Integer> elements, Integer element) {
        List<Integer> result = new ArrayList<>();

        for(Integer elementFound : elements) {
            if(elementFound != element) {
                result.add(elementFound);
            }
        }

        return result;
    }

    protected Integer findMiddleValue(List<Integer> pages) {
        int middle = pages.size() / 2;
        return pages.get(middle);
    }

    protected List<String> groupsFromFile(String inputFilePath, String regex) {
        List<String> res = new ArrayList<>();
        List<String> lines = this.utils.readLinesOfFile(inputFilePath);
        Pattern pattern = Pattern.compile(regex);
        for(String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                res.add(matcher.group());
            }
        }
        return res;
    }

    protected Map<Integer, List<Integer>> turnRulesIntoMap(List<String> rulesInput) {
        Map<Integer, List<Integer>> res = new HashMap<>();
        for(String rule : rulesInput) {
            String[] ruleArray = rule.split("\\|");
            if(res.get(Integer.valueOf(ruleArray[0])) == null) {
                res.put(Integer.valueOf(ruleArray[0]), List.of(Integer.valueOf(ruleArray[1])));
            }
            else {
                List<Integer> currentVals = new ArrayList<>(res.get(Integer.valueOf(ruleArray[0])));
                currentVals.add(Integer.valueOf(ruleArray[1]));
                res.put(Integer.valueOf(ruleArray[0]), currentVals);
            }
        }
        return res;
    }

    protected List<List<Integer>> turnPagesIntoList(List<String> rulesInput) {
        List<List<Integer>> res = new ArrayList<>();
        for(String rule : rulesInput) {
            res.add(Arrays.stream(rule.split(","))
                    .map(Integer::valueOf)
                    .toList());
        }
        return res;
    }
}