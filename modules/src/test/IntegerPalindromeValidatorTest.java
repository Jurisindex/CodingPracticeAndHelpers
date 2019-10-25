package src.test;

import org.junit.jupiter.api.Test;
import src.examSources.IntegerIsPalindrome;

class IntegerIsPalindromeTest
{
	@Test
	void testSimpleNegativeCase()
	{
		int i = -121;
		boolean output = IntegerIsPalindrome.isIntegerPalindrome(i);
		System.out.println(output);
		assert output == false;
	}

	@Test
	void testSimplePositiveCase()
	{
		int i = 121;
		boolean output = IntegerIsPalindrome.isIntegerPalindrome(i);
		System.out.println(output);
		assert output == true;
	}

	@Test
	void testSimplePositiveZeroCase()
	{
		int i = 1210;
		boolean output = IntegerIsPalindrome.isIntegerPalindrome(i);
		System.out.println(output);
		assert output == false;
	}

	@Test
	void testSimpleNegativeZeroLeadingCase()
	{
		int i = 01210;
		boolean output = IntegerIsPalindrome.isIntegerPalindrome(i);
		System.out.println(output);
		assert output == false;
	}

	@Test
	void testSimplePositiveEvenCase()
	{
		int i = 1221;
		boolean output = IntegerIsPalindrome.isIntegerPalindrome(i);
		System.out.println(output);
		assert output == true;
	}

	@Test
	void testSimpleNegativeEvenCase()
	{
		int i = 1212;
		boolean output = IntegerIsPalindrome.isIntegerPalindrome(i);
		System.out.println(output);
		assert output == false;
	}
}