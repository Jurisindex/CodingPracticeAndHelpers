package examSources.facebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzlesTest {
    Puzzles puzzles = new Puzzles();

    @Test
    void getMaxAdditionalDinersCount() {
        long result = puzzles.getMaxAdditionalDinersCount(10,1,2, new long[]{2L, 6L});
        assertEquals(3L,result);
        long result0 = puzzles.getMaxAdditionalDinersCount(15,2,3, new long[]{11L, 6L, 14L});
        assertEquals(1L,result0);
        long result1 = puzzles.getMaxAdditionalDinersCount(10, 1, 0, new long[]{});
        assertEquals(result1, 5L);
        long result2 = puzzles.getMaxAdditionalDinersCount(10, 1, 1, new long[]{5L});
        assertEquals(result2, 4L);
        long result3 = puzzles.getMaxAdditionalDinersCount(10, 1, 3, new long[]{2L, 6L, 8L});
        assertEquals(result3, 2L);
        long result4 = puzzles.getMaxAdditionalDinersCount(1000000, 1000, 5, new long[]{25000L, 500000L, 800000L, 900000L, 999999L});
        assertEquals(result4, 991L);
        long result5 = puzzles.getMaxAdditionalDinersCount(Long.MAX_VALUE, Long.MAX_VALUE - 1, 2, new long[]{Long.MAX_VALUE - 1, Long.MAX_VALUE});
        assertEquals(result5, 1L);
        // Expected result: 2 (maximum range and gap, only 2 seats occupied)

    }

    @Test
    void getArtisticPhotographCount() {
        int result1 = puzzles.getArtisticPhotographCount(5, "APABA",1,2);
        assertEquals(1, result1);
        int result2 = puzzles.getArtisticPhotographCount(8, ".PBAAP.B",1,3);
        assertEquals(3, result2);
        int result3 = puzzles.getArtisticPhotographCount(10, "PAA.B.BP.A", 2, 3);
        assertEquals(1, result3);
        int result4 = puzzles.getArtisticPhotographCount(10, "BBAAPPAABB", 1, 55);
        assertEquals(16, result4);
        int result5 = puzzles.getArtisticPhotographCount(33, ".A.A.A.B.B.A.A.P.P.A.A.B.B.A.A.A.", 1, 55);
        assertEquals(16, result5);
        int result6 = puzzles.getArtisticPhotographCount(7, "ABAPB.A", 1, 2);
        assertEquals(1, result6);
        int result7 = puzzles.getArtisticPhotographCount(9, "AP.A.BAPA", 2, 3);
        assertEquals(1, result7);
    }

    @Test
    void getArtisticPhotographCountOnePass() {
//        int result1 = puzzles.getArtisticPhotographCountOnePass(5, "APABA",1,2);
//        assertEquals(1, result1);
        int result2 = puzzles.getArtisticPhotographCountOnePass(8, ".PBAAP.B",1,3);
        assertEquals(3, result2);
        int result3 = puzzles.getArtisticPhotographCountOnePass(10, "PAA.B.BP.A", 2, 3);
        assertEquals(1, result3);
        int result4 = puzzles.getArtisticPhotographCountOnePass(10, "BBAAPPAABB", 1, 55);
        assertEquals(16, result4);
        int result5 = puzzles.getArtisticPhotographCountOnePass(33, ".A.A.A.B.B.A.A.P.P.A.A.B.B.A.A.A.", 1, 55);
        assertEquals(16, result5);
        int result6 = puzzles.getArtisticPhotographCountOnePass(7, "ABAPB.A", 1, 2);
        assertEquals(1, result6);
        int result7 = puzzles.getArtisticPhotographCountOnePass(9, "AP.A.BAPA", 2, 3);
        assertEquals(1, result7);
    }

    @Test
    void getHitProbability() {
        //stub
    }

    @Test
    void getMaximumEatenDishCount() {
        int result1 = puzzles.getMaximumEatenDishCount(6, new int[]{1,2,3,3,2,1}, 1);
        assertEquals(5, result1);
        int result2 = puzzles.getMaximumEatenDishCount(6, new int[]{1,2,3,3,2,1}, 2);
        assertEquals(4, result2);
        int result3 = puzzles.getMaximumEatenDishCount(7, new int[]{1,2,1,2,1,2,1}, 2);
        assertEquals(2, result3);
    }

    @Test
    void getMinCodeEntryTime() {
//        long result1 = puzzles.getMinCodeEntryTime(3, 3, new int[]{1,2,3});
//        assertEquals(2L, result1);
        long result2 = puzzles.getMinCodeEntryTime(45000, 5, new int[]{2,5,4500,44995,5});
        assertEquals(9014L, result2);
    }

    @Test
    void getMinProblemCount() {
        int result1 = puzzles.getMinProblemCount(6, new int[]{1,2,3,4,5,6});
        assertEquals(4, result1);
        int result2 = puzzles.getMinProblemCount(4, new int[]{4,3,3,4});
        assertEquals(3, result2);
        int result3 = puzzles.getMinProblemCount(4, new int[]{2,4,6,8});
        assertEquals(4, result3);
    }

    @Test
    void getMinimumDeflatedDiscCount() {
        int result1 = puzzles.getMinimumDeflatedDiscCount(5, new int[]{2,5,3,6,5});
        assertEquals(3, result1);
        int result2 = puzzles.getMinimumDeflatedDiscCount(3, new int[]{100,100,100});
        assertEquals(2, result2);
        int result3 = puzzles.getMinimumDeflatedDiscCount(4, new int[]{6,5,4,3});
        assertEquals(-1, result3);
    }

    @Test
    void getUniformIntegerCountInInterval() {
        int result1 = puzzles.getUniformIntegerCountInInterval(75L, 300L);
        assertEquals(5, result1);
        int result2 = puzzles.getUniformIntegerCountInInterval(1L, 9L);
        assertEquals(9, result2);
        int result3 = puzzles.getUniformIntegerCountInInterval(999999999999L, 999999999999L);
        assertEquals(1, result3);
        int result4 = puzzles.getUniformIntegerCountInInterval(12L, 999999999998L);
        assertEquals(97, result4);
        int result5 = puzzles.getUniformIntegerCountInInterval(7L, 6665L);
        assertEquals(26, result5);
    }

    @Test
    void getSecondsRequired() {
        long result1 = puzzles.getSecondsRequired(3, 1, new long[]{1});
        assertEquals(2L, result1);
        long result2 = puzzles.getSecondsRequired(6, 3, new long[]{5,2,4});
        assertEquals(4L, result2);
    }

    @Test
    void getMaxExpectedProfit() {
        double epsilon = 0.000001;
        double result1 = puzzles.getMaxExpectedProfit(5, new int[]{10,2,8,6,4}, 5, 0.0);
        assertTrue(Math.abs(25.000000 - result1) < epsilon);
        double result2 = puzzles.getMaxExpectedProfit(5, new int[]{10,2,8,6,4}, 5, 1.0);
        assertTrue(Math.abs(9.000000 - result2) < epsilon);
        double result3 = puzzles.getMaxExpectedProfit(5, new int[]{10,2,8,6,4}, 3, 0.5);
        assertTrue(Math.abs(17.000000 - result3) < epsilon);
        double result4 = puzzles.getMaxExpectedProfit(5, new int[]{10,2,8,6,4}, 3, 0.15);
        assertTrue(Math.abs(20.108250 - result4) < epsilon);
        double dontgoinanyday = puzzles.getMaxExpectedProfit(5, new int[]{1,1,1,1,1}, 10, 0.85);
        assertTrue(Math.abs(0.0 - dontgoinanyday) < epsilon);
        double goineveryday = puzzles.getMaxExpectedProfit(5, new int[]{99,99,99,99,99}, 10, 0.85);
        assertTrue(Math.abs(445 - goineveryday) < epsilon);
        double dontgoinonlyfirstandlastday = puzzles.getMaxExpectedProfit(5, new int[]{1,99,99,99,1}, 10, 0.85);
        assertTrue(Math.abs(267.15 - dontgoinonlyfirstandlastday) < epsilon);
        double onlyWorthAtEndButNoSteal = puzzles.getMaxExpectedProfit(10, new int[]{1,1,1,1,1,1,1,1,1,100}, 10, 0.0);
        assertTrue(Math.abs(99 - onlyWorthAtEndButNoSteal) < epsilon);
        double only1ProfitAtEnd = puzzles.getMaxExpectedProfit(10, new int[]{10,10,10,10,10,10,10,10,10,11}, 100, 0.0);
        assertTrue(Math.abs(1 - only1ProfitAtEnd) < epsilon);
        double ending0ButWorthToTake = puzzles.getMaxExpectedProfit(12, new int[]{10,10,10,10,10,10,10,10,10,11,10,0}, 100, 0.0);
        assertTrue(Math.abs(11 - ending0ButWorthToTake) < epsilon);


    }

    @Test
    void getSecondsRequiredPortals() {
        int sample1 = puzzles.getSecondsRequired(3, 3, new char[][]{{'.','E','.'},{'.','#','E'},{'.','S','E'}});
        assertEquals(4, sample1);
        int sample2 = puzzles.getSecondsRequired(3, 4, new char[][]{{'a','.','S','a'},{'#','#','#','#'},{'E','b','.','b'}});
        assertEquals(-1, sample2);
        int sample3 = puzzles.getSecondsRequired(3, 4, new char[][]{{'a','S','.','b'},{'#','#','#','#'},{'E','b','.','a'}});
        assertEquals(4, sample3);
        int sample4 = puzzles.getSecondsRequired(1, 9, new char[][]{{'x','S','.','.','x','.','.','E','x'}});
        assertEquals(3, sample4);
    }
}