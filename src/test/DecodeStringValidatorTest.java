package test;

import examSources.DecodeStringValidator;
import examSources.Find2dWords;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecodeStringValidatorTest {

    @Test
    void decodeStringCase1()
    {
        String input = "3[a]2[bc]";
        String expected = "aaabcbc";
        String decodedString = DecodeStringValidator.decodeString(input);
        assertEquals(expected, decodedString);
    }

    @Test
    void decodeStringCase2()
    {
        String input = "3[a2[c]]";
        String expected = "accaccacc";
        String decodedString = DecodeStringValidator.decodeString(input);
        assertEquals(expected, decodedString);
    }

    @Test
    void decodeStringCase3()
    {
        String input = "2[abc]3[cd]ef";
        String expected = "abcabccdcdcdef";
        String decodedString = DecodeStringValidator.decodeString(input);
        assertEquals(expected, decodedString);
    }

    @Test
    void decodeStringCase4()
    {
        String input = "abc3[cd]xyz";
        String expected = "abccdcdcdxyz";
        String decodedString = DecodeStringValidator.decodeString(input);
        assertEquals(expected, decodedString);
    }

    @Test
    void decodeStringCase5()
    {
        String input = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        String expected = "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef";
        String decodedString = DecodeStringValidator.decodeString(input);
        assertEquals(expected, decodedString);
    }
}