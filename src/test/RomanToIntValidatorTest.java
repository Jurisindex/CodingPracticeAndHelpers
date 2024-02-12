package test;

import org.junit.jupiter.api.Test;
import examSources.RomanToIntValidator;

import static org.junit.jupiter.api.Assertions.*;

class RomanToIntValidatorTest
{

	@Test
	void romanToIntCase()
	{
		Integer expectedOutput =  1994;
		assert RomanToIntValidator.romanToInt("MCMXCIV").equals(expectedOutput);
	}
}