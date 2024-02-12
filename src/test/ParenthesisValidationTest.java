package test;

import org.junit.jupiter.api.Test;
import examSources.ParenthesisValidator;

class ParenthesisValidationTest
{
	@Test
	public void testPositiveCase()
	{
		boolean result = ParenthesisValidator.isValidParensGrouping("(())()");
		assert (result == true);
	}

	@Test
	public void testNegativeCase()
	{
		boolean result = ParenthesisValidator.isValidParensGrouping(")(())()(");
		assert (result == false);
	}
}