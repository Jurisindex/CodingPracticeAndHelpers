package test;

import examSources.LetterTilePossibilitiesValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterTilePossibilitiesValidatorTest
{
    @Test
    void LetterTilePossibilitiesTest1()
    {
        String input = "AAB";
        int output = LetterTilePossibilitiesValidator.numTilePossibilities(input);
        assertEquals(8, output);
    }
    @Test
    void LetterTilePossibilitiesTest2()
    {
        String input = "AAABBC";
        int output = LetterTilePossibilitiesValidator.numTilePossibilities(input);
        assertEquals(188, output);
    }
}