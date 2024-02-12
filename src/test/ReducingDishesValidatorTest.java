package test;

import org.junit.jupiter.api.Test;
import examSources.ReducingDishesValidator;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReducingDishesValidatorTest
{

	@Test
	void maxSatisfaction1()
	{
		int[] nums = {4,3,2};
		int out = ReducingDishesValidator.maxSatisfaction(nums);
		assert (out == 20);
	}

	@Test
	void maxSatisfaction2()
	{
		int[] nums = {-1,-8,0,5,-7};
		int out = ReducingDishesValidator.maxSatisfaction(nums);
		assert (out == 14);
	}
}