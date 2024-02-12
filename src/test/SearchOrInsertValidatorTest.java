package test;

import org.junit.jupiter.api.Test;
import examSources.FruitCollectionValidator;
import examSources.SearchOrInsertValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchOrInsertValidatorTest
{
	//[1,3,5,6], 5

	@Test
	void smallHappyCase()
	{
		Integer[] nums = {1,3,5,6};
		Integer target = 5;
		Integer expectedOutput = 2;
		Integer actualOutput = methodWrapper(nums, target);
		assert expectedOutput.equals(actualOutput);
	}

	private Integer methodWrapper(Integer[] nums, Integer target)
	{
		List<Integer> treeList = new ArrayList<>();
		for(int i = 0; i < nums.length; i++)
		{
			treeList.add(nums[i]);
		}

		return SearchOrInsertValidator.searchOrInsert(treeList, target);
	}
}