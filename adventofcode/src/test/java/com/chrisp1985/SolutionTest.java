package com.chrisp1985;

import com.chrisp1985.adventofcode.day1.DayOnePtOne;
import com.chrisp1985.adventofcode.day1.DayOnePtTwo;
import com.chrisp1985.adventofcode.day10.DayTen;
import com.chrisp1985.adventofcode.day11.DayEleven;
import com.chrisp1985.adventofcode.day12.DayTwelve;
import com.chrisp1985.adventofcode.day14.DayFourteen;
import com.chrisp1985.adventofcode.day15.DayFifteen;
import com.chrisp1985.adventofcode.day16.DaySixteen;
import com.chrisp1985.adventofcode.day2.DayTwoPtOne;
import com.chrisp1985.adventofcode.day2.DayTwoPtTwo;
import com.chrisp1985.adventofcode.day3.DayThreePtOne;
import com.chrisp1985.adventofcode.day3.DayThreePtTwo;
import com.chrisp1985.adventofcode.day4.DayFourPtOne;
import com.chrisp1985.adventofcode.day4.DayFourPtTwo;
import com.chrisp1985.adventofcode.day5.DayFivePtOnePtTwo;
import com.chrisp1985.adventofcode.day6.DaySix;
import com.chrisp1985.adventofcode.day7.DaySeven;
import com.chrisp1985.adventofcode.day8.DayEight;
import com.chrisp1985.adventofcode.day9.DayNine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    // DAY 1

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

    // DAY 2

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

    // DAY 3
    @Test
    public void dayThreePtOneTest() {
        DayThreePtOne dayThreePtOne = new DayThreePtOne();
        dayThreePtOne.solution("day3.txt");
    }

    @Test
    public void dayThreePtTwoTest() {
        DayThreePtTwo dayThreePtTwo = new DayThreePtTwo();
        dayThreePtTwo.solution("day3.txt");
    }

    // DAY 4

    @Test
    public void dayFourPtOneTest() {
        DayFourPtOne dayFourPtOne = new DayFourPtOne();
        dayFourPtOne.solution("day4.txt");
    }

    @Test
    public void dayFourPtTwoTest() {
        DayFourPtTwo dayFourPtTwo = new DayFourPtTwo();
        dayFourPtTwo.solution("day4.txt");
    }

    // DAY 5
    @Test
    public void dayFivePtOneTest() {
        new DayFivePtOnePtTwo().solution("day5.txt");
    }

    // DAY 6
    @Test
    public void daySixTest() {
        new DaySix().solution("day6.txt");
    }

    @Test
    public void daySevenTest() {
        new DaySeven().solution("day7.txt");
    }

    @Test
    public void dayEightTest() {
        new DayEight().solution("day8.txt");
    } // 2D Array.

    @Test
    public void dayNineTest() {
        new DayNine().solution("day9.txt");
    } // Reorganising an array working backwards.

    @Test
    public void dayTenTest() {
        new DayTen().solution("day10.txt");
    } // 2D Array with Recursion.

    @Test
    public void dayElevenTest() {
        new DayEleven().solution("day11.txt");
    } // Recursion with Memo.

    @Test
    public void dayTwelveTest() {
        new DayTwelve().solution("day12.txt");
    }

    @Test
    public void dayFourteenTest() {
        new DayFourteen().solution("day14.txt");
    }

    @Test
    public void dayFifteenTest() {
        new DayFifteen().solution("day15.txt");
    }

    @Test
    public void daySixteenTest() {
        new DaySixteen().solution("day16_sample.txt");
    }
}
