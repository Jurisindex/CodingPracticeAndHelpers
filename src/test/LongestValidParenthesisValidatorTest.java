package test;

import org.junit.jupiter.api.Test;
import examSources.LongestValidParenthesisValidator;

import static org.junit.jupiter.api.Assertions.*;

class LongestValidParenthesisValidatorTest
{

	@Test
	void getLongestValidParenthesisSubstringLongLongTestCase()
	{
		String input = "()()()()()()()()()))))))(((((((((())))))))())())";
		String output = LongestValidParenthesisValidator.getLongestValidParenthesisSubstring(input);
		assert output.equals("(((((((((())))))))())())");
	}

	@Test
	void getLongestValidParenthesisSubstringSimple()
	{
		String input = "(((((()()";
		String output = LongestValidParenthesisValidator.getLongestValidParenthesisSubstring(input);
		assert output.equals("()()");
	}

	@Test
	void getLongestValidParenthesisSubstringNoValid()
	{
		String input = ")))))))))))))))))))))))))((((((((((";
		String output = LongestValidParenthesisValidator.getLongestValidParenthesisSubstring(input);
		assert output.equals("");
	}
}