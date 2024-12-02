package com.chrisp1985;

import com.chrisp1985.adventofcode.day1.DayOnePtOne;
import com.chrisp1985.adventofcode.day1.DayOnePtTwo;
import com.chrisp1985.adventofcode.day2.DayTwoPtOne;
import com.chrisp1985.adventofcode.day2.DayTwoPtTwo;
import org.junit.jupiter.api.Assertions;
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


    @Test
    public void dayTwoPtOneTest() {
        DayTwoPtOne dayTwo = new DayTwoPtOne();
        dayTwo.solution("day2.txt");
    }

    @Test
    public void dayTwoPtTwoTest() {
        DayTwoPtTwo dayTwo = new DayTwoPtTwo();
        Assertions.assertFalse(dayTwo.areAllUniformWithTolerance(new int[]{18, 21, 23, 26, 28, 26, 27, 28}, DayTwoPtOne.Rate.INCREASING));
        Assertions.assertFalse(dayTwo.areAllUniformWithTolerance(new int[]{ 5,1,2,5,7,11 }, DayTwoPtOne.Rate.INCREASING));
        Assertions.assertTrue(dayTwo.areAllUniformWithTolerance(new int[]{ 1,4,2,6,7 }, DayTwoPtOne.Rate.INCREASING));
        Assertions.assertTrue(dayTwo.areAllUniformWithTolerance(new int[]{ 10,17,14,11,8,7 }, DayTwoPtOne.Rate.DECREASING));
        Assertions.assertFalse(dayTwo.areAllUniformWithTolerance(new int[]{ 73,70,72,75,78,79,81,85 }, DayTwoPtOne.Rate.INCREASING));
        Assertions.assertTrue(dayTwo.areAllUniformWithTolerance(new int[]{ 50,51,53,55,58,60,63,64 }, DayTwoPtOne.Rate.INCREASING));

        Assertions.assertFalse(dayTwo.areAllUniformWithTolerance(new int[]{ 5,1,2,6,7 }, DayTwoPtOne.Rate.INCREASING));
        Assertions.assertFalse(dayTwo.areAllUniformWithTolerance(new int[]{ 11,9,10,13,10,12,13,17 }, DayTwoPtOne.Rate.INCREASING));

        dayTwo.solution("day2.txt");
    }

    @Test
    public void dayThreePtOneTest() {

    }
}
