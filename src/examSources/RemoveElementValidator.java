package examSources;

/*
Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example 1:

Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,1,2,2,3,0,4,2], val = 2,

Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

Note that the order of those five elements can be arbitrary.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeElement(nums, val);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}


 */

public class RemoveElementValidator
{
//	public static int removeElement(int[] nums, int val)
//	{
//		int newLength = nums.length;
//		int itemsSkipped = 0;
//		//iterate through
//		//if it's a hit, swap it with the last element, and decrement the total length number
//		//return totalLength
//
//		for(int i = 0; i < newLength; i++)
//		{
//			if(nums[i] == val)
//			{
//				int positionToSwap = 0;
//				for(int j = newLength-1; j >= 0; j--)
//				{
//					if(j == 0) return 0;
//
//					if(nums[j] == val)
//					{
//						//Swapping would be pointless
//						itemsSkipped++;
//					}
//					else
//					{
//						positionToSwap = j;
//						break;
//					}
//				}
//				//int temp = nums[i];
//				nums[i] = nums[positionToSwap];
//				//nums[newLength-1] = temp;
//				newLength--;
//			}
//		}
//		newLength = newLength - itemsSkipped;
//
//		return newLength;
//	}

	public static int removeElement(int[] nums, int val)
	{
		int[] newInts = new int[nums.length];
		int newIntCounter = 0;

		for(Integer i : nums)
		{
			if(i != val)
			{
				newInts[newIntCounter] = i;
				newIntCounter++;
			}
		}

		for(int i = 0; i < newIntCounter; i++)
		{
			nums[i] = newInts[i];
		}

		return newIntCounter;
	}
}
