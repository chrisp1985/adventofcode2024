package com.chrisp1985;

import com.chrisp1985.adventofcode.day1.DayOnePtOne;
import com.chrisp1985.adventofcode.day1.DayOnePtTwo;
import org.junit.jupiter.api.Test;

public class SolutionTest {


    @Test
    public void dayOneTest() {
        DayOnePtOne dayOne = new DayOnePtOne();
        dayOne.solution("day1.txt");
    }

    @Test
    public void dayOnePtTwoTest() {
        DayOnePtTwo dayOnePtTwo = new DayOnePtTwo();
        dayOnePtTwo.solution("day1.txt");
    }
}
