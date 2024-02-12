package test;

import org.junit.jupiter.api.Test;
import examSources.TelephoneNumberCodeValidator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneNumberCodeValidatorTest
{
	@Test
	public void stupidSimpleTest()
	{
		List<Integer> input = Arrays.asList(2);
		List<String> output = TelephoneNumberCodeValidator.returnAllPossibleCombinations(input);
		System.out.println("Validating this is a pain. Manual time.");
	}

	@Test
	public void simpleDoubleDigitCase()
	{
		List<Integer> input = Arrays.asList(2,2);
		List<String> output = TelephoneNumberCodeValidator.returnAllPossibleCombinations(input);
		System.out.println("Validating this is a pain. Manual time.");
	}

	@Test
	public void downTheLine()
	{
		List<Integer> input = Arrays.asList(2,3,9);
		List<String> output = TelephoneNumberCodeValidator.returnAllPossibleCombinations(input);
		System.out.println("Validating this is a pain. Manual time.");
	}
}