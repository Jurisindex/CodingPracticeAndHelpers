package test;

import org.junit.jupiter.api.Test;
import examSources.FindSquareRootOfNumber;

import static org.junit.jupiter.api.Assertions.*;

class FindSquareRootOfNumberTest
{

	@Test
	void findSquareRootOfNumberTwo()
	{
		//Double answer = 1.4142135623730950488016887242097;
		Double input = 2.0;

		System.out.println(FindSquareRootOfNumber.findSquareRootOfNumber(input));
	}

	@Test
	void findSquareRootOfNumberThirtySix()
	{
		Double input = 36.0;

		System.out.println(FindSquareRootOfNumber.findSquareRootOfNumber(input));
	}

	@Test
	void findSquareRootOfFloat91Point25()
	{
		Double input = 91.25;

		System.out.println(FindSquareRootOfNumber.findSquareRootOfNumber(input));
	}
}