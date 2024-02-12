package test;

import org.junit.jupiter.api.Test;
import examSources.RemoveMinimalParenthesisValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoveMinimalParenthesisValidatorTest
{

	@Test
	void returnMinimalParenthesisSampleCase()
	{
		String parensInput = "( ) ) ) ( ( ( )  ";
		List<String> outputs = RemoveMinimalParenthesisValidator.returnMinimalParenthesis(parensInput);
		System.out.println("Manual check step");
	}

	@Test
	void returnMinimalParenthesisSpamInputCase()
	{
		String parensInput = "(Gae())zxc()()))((()";
		List<String> outputs = RemoveMinimalParenthesisValidator.returnMinimalParenthesis(parensInput);
		System.out.println("Manual check step");
	}
}