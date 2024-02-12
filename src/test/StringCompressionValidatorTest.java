package test;

import examSources.StringCompressionValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCompressionValidatorTest {

    @Test
    void compressTest1()
    {
        char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        int output = StringCompressionValidator.compress(chars);
        assertEquals(output, 4);
        assertEquals(chars[0], 'a');
        assertEquals(chars[1], 'b');
        assertEquals(chars[2], '1');
        assertEquals(chars[3], '2');
    }
}