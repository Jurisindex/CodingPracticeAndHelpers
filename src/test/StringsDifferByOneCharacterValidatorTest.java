package test;

import examSources.StringsDifferByOneCharacterValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsDifferByOneCharacterValidatorTest {

    @Test
    void differByOneTest1()
    {
        String[] dict = {"abcd", "acbd", "aacd"};
        boolean result = StringsDifferByOneCharacterValidator.differByOne(dict);
        assertEquals(true, result);
    }

    @Test
    void differByOneTest2()
    {
        String[] dict = {"ab","cd","yz"};
        boolean result = StringsDifferByOneCharacterValidator.differByOne(dict);
        assertEquals(false, result);
    }

    @Test
    void differByOneTest3()
    {
        String[] dict = {"abcd","cccc","abyd","abab"};
        boolean result = StringsDifferByOneCharacterValidator.differByOne(dict);
        assertEquals(true, result);
    }
}