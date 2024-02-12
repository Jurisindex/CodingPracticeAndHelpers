package examSources;

/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.

Follow Up:
Can you do it in O(n) time?
 */

import java.util.Arrays;

public class LongestSubarraySumValidator
{
	public static int maxSubArrayLen(int[] nums, int k)
	{
		Integer rollingSum = 0;
		Integer longestSubarray = 0;

		for(int i = 0; i < nums.length; i++)
		{
			if(i >= nums.length - longestSubarray) break;
			//reach an element. Assume this is the start
			int localLongest = 0;

			for(int j = i; j < nums.length; j++)
			{
				//keep adding until we reach rollingSum = k.
				if(rollingSum == k)
				{
					if(localLongest > longestSubarray) longestSubarray = localLongest;
				}
				rollingSum += nums[j];
				localLongest++;
			}
			if(rollingSum == k)
			{
				if(localLongest > longestSubarray) longestSubarray = localLongest;
			}
			rollingSum = 0;
		}

		return longestSubarray;
	}
}
