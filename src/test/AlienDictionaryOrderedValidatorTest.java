package test;

import org.junit.jupiter.api.Test;
import examSources.AlienDictionaryOrderedValidator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlienDictionaryOrderedValidatorTest
{

	@Test
	void alienDictionarySameEnglishAFalseApproach()
	{
		List<String> dictionaryInput = Arrays.asList("add","addition","additive","addor","adderal");
		String alphabeticalOrder = "abcdefghijklmnopqrstuvwxyz";
		boolean isInOrder = AlienDictionaryOrderedValidator.isAlienDictionaryOrderedIntegerListApproach(dictionaryInput, alphabeticalOrder);
		assert isInOrder == false;
	}

	@Test
	void alienDictionarySameEnglishATrueApproach()
	{
		List<String> dictionaryInput = Arrays.asList("add","addition","additive","addor","asinine");
		String alphabeticalOrder = "abcdefghijklmnopqrstuvwxyz";
		boolean isInOrder = AlienDictionaryOrderedValidator.isAlienDictionaryOrderedIntegerListApproach(dictionaryInput, alphabeticalOrder);
		assert isInOrder == true;
	}
}