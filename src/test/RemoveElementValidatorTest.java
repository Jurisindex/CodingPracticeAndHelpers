package test;

import org.junit.jupiter.api.Test;
import examSources.RemoveElementValidator;

import static org.junit.jupiter.api.Assertions.*;

class RemoveElementValidatorTest
{

	@Test
	void removeElement()
	{
		int[] nums = {3,2,2,3};
		int val = 3;
		System.out.println(RemoveElementValidator.removeElement(nums, val));
		for(Integer i : nums)
		{
			System.out.print(i + ",");
		}
	}

	@Test
	void removeElementOne()
	{
		int[] nums = {1};
		int val = 1;
		System.out.println(RemoveElementValidator.removeElement(nums, val));
		for(Integer i : nums)
		{
			System.out.print(i + ",");
		}
	}
}