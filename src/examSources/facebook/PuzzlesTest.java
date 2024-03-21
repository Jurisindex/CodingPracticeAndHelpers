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
        assertEquals(result5, 2L);
        // Expected result: 2 (maximum range and gap, only 2 seats occupied)

    }
}