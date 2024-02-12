package examSources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PermutationsValidator
{
	public static List<List<Integer>> getPermutationsRecursively(List<Integer> input)
	{
		//This is the array that, given our input, this is our 2-D map output.
		List<List<Integer>> permutationOutputs = new ArrayList<>();

		//Iterate through the list, recursing down for each subList.
		for(int i = 0; i < input.size(); i++)
		{
			//Start with a base
			List<Integer> subList = new ArrayList<>();
			subList.addAll(input);
			List<Integer> base = new ArrayList<>(Arrays.asList(subList.remove(i)));
			//Store every permutation of that base
			List<List<Integer>> permutations = new ArrayList<>();
			//Recurse down the line
			permutations = returnPermutationsStartingWith(base, subList);
			//Add the current index's list of combinations to the output.
			for(List<Integer> list : permutations)
			{
				if(!permutationOutputs.contains(list))
				{
					permutationOutputs.add(list);
				}
			}
			//permutationOutputs.addAll(permutations);
		}

		return permutationOutputs;
	}

	private static List<List<Integer>> returnPermutationsStartingWith(List<Integer> base, List<Integer> subList)
	{
		if(subList.isEmpty())
		{
			return new ArrayList<>(new ArrayList<>(Arrays.asList(base)));
		}

		List<List<Integer>> greaterPermutationsOutput = new ArrayList<>();

		//Iterate through the list, recursing down for each subList.
		for(int i = 0; i < subList.size(); i++)
		{
			//Start with a base
			List<Integer> subSubList = new ArrayList<>();
			subSubList.addAll(subList);
			List<Integer> newBase = new ArrayList<>();
			newBase.addAll(base);
			newBase.add(subSubList.remove(i));
			//Store every permutation of that base
			List<List<Integer>> permutations = new ArrayList<>();
			//Recurse down the line
			permutations = returnPermutationsStartingWith(newBase, subSubList);
			//Add the current index's list of combinations to the output.
			greaterPermutationsOutput.addAll(permutations);
		}

		return greaterPermutationsOutput;
	}

	public static List<List<Integer>> getPermutationsIteratively(List<Integer> input)
	{
		List<List<Integer>> permutationOutputs = new ArrayList<>();

		List<Integer> initialList = new ArrayList<>();
		initialList.addAll(input);

		Integer swapFirstIndex = 0;
		Integer swapSecondIndex = -1;

		while(swapFirstIndex != input.size()-2 && swapSecondIndex != input.size()-1)
		{
			//moving pointers, ever going backwards down the list
			if(swapSecondIndex.equals(input.size()-1))
			{
				swapFirstIndex++;
				swapSecondIndex = swapFirstIndex+1;
			}
			else
			{
				swapSecondIndex++;
			}
			List<Integer> swapList = new ArrayList<>();
			swapList.addAll(initialList);
			Collections.swap(swapList, swapFirstIndex, swapSecondIndex);

			if(!permutationOutputs.contains(swapList))
			{
				permutationOutputs.add(swapList);
			}
			Integer counter = 0;
			while(counter < input.size())
			{
				List<Integer> rotateList = new ArrayList<>();
				rotateList.addAll(swapList);
				rotateList = rotate(rotateList);
				counter++;
				if(!permutationOutputs.contains(rotateList))
				{
					permutationOutputs.add(rotateList);
				}
				swapList = rotateList;
			}
		}


		for(int i = 0; i < input.size(); i++)
		{

		}

		return permutationOutputs;
	}

	private static List<Integer> rotate(List<Integer> initialList)
	{
		List<Integer> rotatedList = initialList;
		Integer putAtFront = rotatedList.remove(rotatedList.size()-1);
		rotatedList.add(0, putAtFront);

		return rotatedList;
	}
}