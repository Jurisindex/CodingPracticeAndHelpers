package test;

import org.junit.jupiter.api.Test;
import examSources.RectangleOverlapValidator;

import static org.junit.jupiter.api.Assertions.*;

class RectangleOverlapValidatorTest
{

	@Test
	void isRectangleOverlapPositiveCase()
	{
		int[] rectA = new int[] {1,1,4,4};
		int[] rectB = new int[] {0,1,1,4};
		assert RectangleOverlapValidator.isRectangleOverlap(rectA, rectB) == false;
	}

	@Test
	void isRectangleOverlapNegativeCase()
	{
		int[] rectA = new int[] {1,1,4,4};
		int[] rectB = new int[] {0,1,4,2};
		assert RectangleOverlapValidator.isRectangleOverlap(rectA, rectB) == true;
	}

}