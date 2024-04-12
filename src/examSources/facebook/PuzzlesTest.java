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
        long result1 = puzzles.getMinCodeEntryTime(3, 3, new int[]{1,2,3});
        assertEquals(2L, result1);
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
        double fiftyfifty = puzzles.getMaxExpectedProfit(12, new int[]{0,50,50,50,0,50,50,50,0,50,50,1}, 10, 0.1);
        assertTrue(Math.abs(336 - fiftyfifty) < epsilon);
    }

    @Test
    void getSecondsRequiredPortals() {
//        int sample1 = puzzles.getSecondsRequired(3, 3, new char[][]{{'.','E','.'},{'.','#','E'},{'.','S','#'}});
//        assertEquals(4, sample1);
//        int sample1modified = puzzles.getSecondsRequired(3, 3, new char[][]{{'.','E','.'},{'.','#','E'},{'.','S','E'}});
//        assertEquals(1, sample1modified);
//        int sample2 = puzzles.getSecondsRequired(3, 4, new char[][]{{'a','.','S','a'},{'#','#','#','#'},{'E','b','.','b'}});
//        assertEquals(-1, sample2);
//        int sample3 = puzzles.getSecondsRequired(3, 4, new char[][]{{'a','S','.','b'},{'#','#','#','#'},{'E','b','.','a'}});
//        assertEquals(4, sample3);
//        int sample4 = puzzles.getSecondsRequired(1, 9, new char[][]{{'x','S','.','.','x','.','.','E','x'}});
//        assertEquals(3, sample4);
//        int sample5 = puzzles.getSecondsRequired(3, 4, new char[][]{
//                        {'a','S','E','b'},
//                        {'#','#','#','#'},
//                        {'E','b','.','a'}});
//        assertEquals(1, sample5);
        int complex = puzzles.getSecondsRequired(0, 0, new char[][]{
                {'a','S','.','b'},
                {'#','#','#','#'},
                {'a','y','q','w'},
                {'#','#','#','#'},
                {'b','x','n','E'},
                {'#','#','#','#'},
                {'E','y','.','b'},
        });
        assertEquals(5, complex);
    }

    @Test
    void getMaxVisitableWebpages() {
//        int maxPagesComplex = puzzles.getMaxVisitableWebpages(0, new int[]{12,1,2,6,3,5,6,11,10,7,10,8});
//        assertEquals(maxPagesComplex, 10);
//        int maxPages1 = puzzles.getMaxVisitableWebpages(4, new int[]{4,1,2,1});
//        assertEquals(maxPages1, 4);
//        int maxPages2 = puzzles.getMaxVisitableWebpages(5, new int[]{4,3,5,1,2});
//        assertEquals(maxPages2, 3);
        int maxPages3 = puzzles.getMaxVisitableWebpages(5, new int[]{2,4,2,2,3});
        assertEquals(maxPages3, 4);
    }

    @Test
    void getMinCodeEntryTimeTwoLocks() {
        long result1 = puzzles.getMinCodeEntryTimeTwoLocks(3, 3, new int[]{1,2,3});
        assertEquals(2L, result1);
        long result2 = puzzles.getMinCodeEntryTimeTwoLocks(10, 4, new int[]{9,4,4,8});
        assertEquals(6L, result2);
    }

    @Test
    void getMaxAdditionalDinersCountLinear() {
        long resultlinear = puzzles.getMaxAdditionalDinersCountLinear(10,1,2, new long[]{2L, 6L});
        assertEquals(3L,resultlinear);
        long result0linear = puzzles.getMaxAdditionalDinersCountLinear(15,2,3, new long[]{11L, 6L, 14L});
        assertEquals(1L,result0linear);
        long result1linear = puzzles.getMaxAdditionalDinersCountLinear(10, 1, 0, new long[]{});
        assertEquals(result1linear, 5L);
        long result2linear = puzzles.getMaxAdditionalDinersCountLinear(10, 1, 1, new long[]{5L});
        assertEquals(result2linear, 4L);
        long result3linear = puzzles.getMaxAdditionalDinersCountLinear(10, 1, 3, new long[]{2L, 6L, 8L});
        assertEquals(result3linear, 2L);
        long result4linear = puzzles.getMaxAdditionalDinersCountLinear(1000000, 1000, 5, new long[]{25000L, 500000L, 800000L, 900000L, 999999L});
        assertEquals(result4linear, 991L);
        long result5linear = puzzles.getMaxAdditionalDinersCountLinear(Long.MAX_VALUE, Long.MAX_VALUE - 1, 2, new long[]{Long.MAX_VALUE - 1, Long.MAX_VALUE});
        assertEquals(result5linear, 1L);
    }

    @Test
    void getMinProblemCountGeneralized() {
        //stub
    }

    @Test
    void getSecondsElapsed() {
//        long result1 = puzzles.getSecondsElapsed(10L,2, new long[]{1L,6L}, new long[]{3,7}, 7L);
//        assertEquals(result1, 22L);
//        long result2 = puzzles.getSecondsElapsed(50L,3, new long[]{39L,19L,28L}, new long[]{49L,27L,35L}, 15L);
//        assertEquals(result2, 35L);
        long result1 = puzzles.getSecondsElapsed(10L, 2, new long[]{1L, 6L}, new long[]{3L, 7L}, 18L);
        assertEquals(result1, 57L);
        long result2 = puzzles.getSecondsElapsed(10L, 2, new long[]{1L, 6L}, new long[]{3L, 7L}, 17L);
        assertEquals(result2, 53L);
        long result3 = puzzles.getSecondsElapsed(10L, 2, new long[]{1L, 6L}, new long[]{3L, 7L}, 12L);
        assertEquals(result3, 37L);
        long result4 = puzzles.getSecondsElapsed(20L, 4, new long[]{5L, 10L, 15L, 18L}, new long[]{7L, 13L, 17L, 20L}, 42L);
        assertEquals(result4, 96L);
        long result5 = puzzles.getSecondsElapsed(15L, 3, new long[]{3L, 8L, 11L}, new long[]{6L, 10L, 14L}, 30L);
        assertEquals(result5, 57L);
        long result6 = puzzles.getSecondsElapsed(12L, 1, new long[]{3L}, new long[]{7L}, 5L);
        assertEquals(result6, 16L);
        long result7 = puzzles.getSecondsElapsed(20L, 1, new long[]{5L}, new long[]{15L}, 10L);
        assertEquals(result7, 15L);
        long result8 = puzzles.getSecondsElapsed(25L, 2, new long[]{6L, 17L}, new long[]{12L, 22L}, 50L);
        assertEquals(result8, 112L);
        long result9 = puzzles.getSecondsElapsed(30L, 3, new long[]{5L, 13L, 19L}, new long[]{10L, 17L, 25L}, 100L);
        assertEquals(result9, 200L);
    }

    @Test
    void getMaxDamageDealt() {
        double epsilon = 0.000001;
        double result1 = puzzles.getMaxDamageDealt(3, new int[]{2,1,4}, new int[]{3,1,2}, 4);
        assertTrue(Math.abs(6.500000 - result1) < epsilon);
        double result2 = puzzles.getMaxDamageDealt(4, new int[]{1,1,2,100}, new int[]{1,2,1,3}, 8);
        assertTrue(Math.abs(62.750000 - result2) < epsilon);
        double result3 = puzzles.getMaxDamageDealt(4, new int[]{1,1,2,3}, new int[]{1,2,1,100}, 8);
        assertTrue(Math.abs(62.750000 - result3) < epsilon);
    }

    @Test
    void getMinExpectedHorizontalTravelDistance() {
        double epsilon = 0.000001;
        double result1 = puzzles.getMinExpectedHorizontalTravelDistance(2, new int[]{10,20}, new int[]{100000,400000}, new int[]{600000,800000});
        assertTrue(Math.abs(155000.00000000 - result1) < epsilon);
        double result2 = puzzles.getMinExpectedHorizontalTravelDistance(5, new int[]{2,8,5,9,4}, new int[]{5000, 2000, 7000, 9000, 0}, new int[]{7000, 8000, 11000, 11000, 4000});
        assertTrue(Math.abs(36.50000000 - result2) < epsilon);
    }
}