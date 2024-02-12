package test;

import org.junit.jupiter.api.Test;
import examSources.LongestSubarraySumValidator;

import static org.junit.jupiter.api.Assertions.*;

class LongestSubarraySumValidatorTest
{
	@Test
	void longestSubarrayTestHappy()
	{
		int[] nums =  {1, -1, 5, -2, 3};
		int target = 3;
		int result = LongestSubarraySumValidator.maxSubArrayLen(nums, target);
		assert result == 4;
	}

	@Test
	void longestSubarrayTestUpTillLast()
	{
		int[] nums =  {-1};
		int target = -1;
		int result = LongestSubarraySumValidator.maxSubArrayLen(nums, target);
		assert result == 1;
	}

	@Test
	void longestSubarrayTestLCWrong()
	{
		int[] nums =  {-1,1};
		int target = 0;
		int result = LongestSubarraySumValidator.maxSubArrayLen(nums, target);
		assert result == 2;
	}

	@Test
	void longestSubArrayTestLCWrong2()
	{
		int[] nums =  {-2,1,-3,4,-1,2,1,-5,4};
		int target = 0;
		int result = LongestSubarraySumValidator.maxSubArrayLen(nums, target);
		assert result == 4;
	}
}