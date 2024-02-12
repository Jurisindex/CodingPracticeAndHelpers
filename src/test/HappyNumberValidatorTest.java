package test;

import org.junit.jupiter.api.Test;
import examSources.HappyNumberValidator;

import static org.junit.jupiter.api.Assertions.*;

class HappyNumberValidatorTest
{

	@Test
	void isHappy()
	{
//		Input: 19
//		Output: true
//		Explanation:
//		12 + 92 = 82
//		82 + 22 = 68
//		62 + 82 = 100
//		12 + 02 + 02 = 1
		int input = 19;
		assert HappyNumberValidator.isHappy(input) == true;
	}

	@Test
	void isHappy7()
	{
		int input = 7;
		assert HappyNumberValidator.isHappy(input) == true;
	}
}