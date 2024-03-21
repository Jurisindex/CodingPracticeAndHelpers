package examSources.facebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzlesTest {
    Puzzles puzzles = new Puzzles();
    @Test
    void testcasedinercount1() {
        long result = puzzles.getMaxAdditionalDinersCount(10,1,2, new long[]{2L, 6L});
        assertEquals(3L,result);
    }

    @Test
    void testcasedinercount2() {
        long result = puzzles.getMaxAdditionalDinersCount(15,2,3, new long[]{11L, 6L, 14L});
        assertEquals(1L,result);
    }

    @Test
    void testcasedinercountBonanza() {
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
}