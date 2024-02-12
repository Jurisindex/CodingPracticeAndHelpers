package examSources;

/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */

import java.util.ArrayList;
import java.util.List;
//WIP TODO this class
public class SumCombinationCandidatesValidator
{
	public static List<List<Integer>> combinationSum(int[] candidates, int target)
	{
		List<List<Integer>> allCombinationSets = new ArrayList<>();
		List<Integer> candidateSetOnI;

		//for each candidate
		for(Integer candidate : candidates)
		{
			//Create a list.
			candidateSetOnI = new ArrayList<>();

			//enter recursive loop that returns a list passed by reference
			List<Integer> candidatesExamined = new ArrayList<>();
			candidatesExamined.add(candidate);
			fullCandidatesListOnI(candidates, candidate, target, candidateSetOnI, candidatesExamined);
			if(!allCombinationSets.contains(candidateSetOnI))
			{
				allCombinationSets.add(candidateSetOnI);
			}
		}

		//assume it's all fine but probably not
		return allCombinationSets;
	}

	protected static List<Integer> fullCandidatesListOnI(int[] candidates, Integer candidate, int target, List<Integer> candidateSetOnI, List<Integer> candidatesExamined)
	{
		//If modulo == 0, return
		candidatesExamined.add(candidate);
		if(target == 0)
		{
			return candidateSetOnI;
		}

		//Divide target by candidates[i]. Also get the modulo.
		Integer candidateRemainder = target - candidate;

		if(candidateRemainder >= 0)
		{
			candidateSetOnI.add(candidate);
		}
		else
		{
			candidatesExamined.add(candidate);
		}

		//If modulo non-zero, recurse
		for(Integer candy : candidates)
		{
			if(!candidatesExamined.contains(candy))
			{
				if(fullCandidatesListOnI(candidates, candy, candidateRemainder, candidateSetOnI, candidatesExamined) != null)
				{
					return candidateSetOnI;
				}
				else
				{
					candidateSetOnI.remove(candidate);
					candidatesExamined.remove(candidate);
				}
			}
		}
		return null;
	}


	public static boolean isPrimeNumber(Integer num)
	{
		for(int i = 2; i <= num/2; i++)
		{
			// condition for nonprime number
			if(num % i == 0)
			{
				return false;
			}
		}
		return true;
	}
}
