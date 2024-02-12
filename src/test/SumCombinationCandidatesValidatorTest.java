package test;

import org.junit.jupiter.api.Test;
import examSources.SumCombinationCandidatesValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SumCombinationCandidatesValidatorTest
{
	/*
	Input: candidates = [2,3,6,7], target = 7,
	A solution set is:
	[
	  [7],
	  [2,2,3]
	]
	 */

	@Test
	void testBasicCase()
	{
		List<List<Integer>> solution = new ArrayList<>();
		List<Integer> setOne = new ArrayList<>();
		setOne.add(7);
		List<Integer> setTwo = new ArrayList<>();
		setTwo.add(2);
		setTwo.add(2);
		setTwo.add(3);
		solution.add(setOne);
		solution.add(setTwo);

		int[] input = {2,3,6,7};
		Integer target = 7;

		List<List<Integer>> output = methodWrapper(input, target);

		//assert they are exactly equal
		for(List<Integer> list : output)
		{
			assert solution.contains(list);
			solution.remove(output);
		}
		assert solution.isEmpty();
	}

	private List<List<Integer>> methodWrapper(int[] input, Integer target)
	{
		List<Integer> numbers = new ArrayList<>();
		for(int i = 0; i < input.length; i++)
		{
			numbers.add(input[i]);
		}

		//TODO: Refactor usage to be List<Integers>. It's probably gonna be better.
		return SumCombinationCandidatesValidator.combinationSum(input, target);
	}
}