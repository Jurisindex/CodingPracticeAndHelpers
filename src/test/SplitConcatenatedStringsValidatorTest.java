package test;

import examSources.SplitConcatenatedStringsValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplitConcatenatedStringsValidatorTest {

    @Test
    void splitLoopedStringTest1()
    {
        String[] strs = {"abc","xyz"};
        String result = SplitConcatenatedStringsValidator.splitLoopedString(strs);
        assertEquals(result, "zyxcba");
    }

    @Test
    void splitLoopedStringTest2()
    {
        String[] strs = {"efg","xyz","abc","xyz"};
        String result = SplitConcatenatedStringsValidator.splitLoopedString(strs);
        assertEquals(result, "zyxgfezyxcba");
    }
}
