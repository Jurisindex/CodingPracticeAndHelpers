package examSources;

import java.util.*;

public class RemoveMinimalParenthesisValidator
{
	public static List<String> returnMinimalParenthesis(String parenthesisInput)
	{
		List<String> minimalParenthesisSets = new ArrayList<>();

		if(ParenthesisValidator.isValidParensGrouping(parenthesisInput))
		{
			minimalParenthesisSets.add(parenthesisInput);
			return minimalParenthesisSets;
		}

		Integer leftRemoveCount = 0;
		Integer rightRemoveCount = 0;
		List<Integer> leftIndecies = new ArrayList<>();
		List<Integer> rightIndecies = new ArrayList<>();

		for(int i = 0; i < parenthesisInput.length(); i++)
		{
			if(parenthesisInput.charAt(i) == '(')
			{
				leftRemoveCount++;
				leftIndecies.add(i);
			}
			else
			if(parenthesisInput.charAt(i) == ')')
			{
				if(leftRemoveCount.equals(0))
				{
					rightRemoveCount++;
				}
				else
				{
					leftRemoveCount--;
				}
				rightIndecies.add(i);
			}
		}

		//( ) ) ) ( ( ( )  left:2 right:2
		List<List<Integer>> leftRemovalCombinations = new ArrayList<>();
		List<List<Integer>> rightRemovalCombinations = new ArrayList<>();

		//Left Parenthesis building.
		for(int i = 0; i < leftIndecies.size(); i++)
		{
			List<Integer> combinationChoices = new ArrayList<>();
			combinationChoices.addAll(leftIndecies);

			List<Integer> currentCombination = new ArrayList<>();
			currentCombination.add(combinationChoices.remove(i));
			List<List<Integer>> greaterCombinations = combinationHelper(combinationChoices, leftRemoveCount-1, currentCombination);

			for(List<Integer> list : greaterCombinations)
			{
				Boolean add = true;
				for(List<Integer> greaterList : leftRemovalCombinations)
				{
					if(list.containsAll(greaterList) && greaterList.containsAll(list))
					{
						add = false;
						break;
					}
				}
				if(add)
				{
					leftRemovalCombinations.add(list);
				}
			}
		}

		//Right Parenthesis building
		for(int i = 0; i < rightIndecies.size(); i++)
		{
			List<Integer> combinationChoices = new ArrayList<>();
			combinationChoices.addAll(rightIndecies);

			List<Integer> currentCombination = new ArrayList<>();
			currentCombination.add(combinationChoices.remove(i));
			List<List<Integer>> greaterCombinations = combinationHelper(combinationChoices, rightRemoveCount-1, currentCombination);

			for(List<Integer> list : greaterCombinations)
			{
				Boolean add = true;
				for(List<Integer> greaterList : rightRemovalCombinations)
				{
					if(list.containsAll(greaterList) && greaterList.containsAll(list))
					{
						add = false;
						break;
					}
				}
				if(add)
				{
					rightRemovalCombinations.add(list);
				}
			}
		}

		//Parenthesis combination
		for(int i = 0; i < leftRemovalCombinations.size(); i++)
		{
			List<Integer> combinationTest = new ArrayList<>();
			combinationTest.addAll(leftRemovalCombinations.get(i));
			for(int j = 0; j < rightRemovalCombinations.size(); j++)
			{
				combinationTest.addAll(rightRemovalCombinations.get(j));
				String postRemovalStringTest = removeParensAtIndecies(parenthesisInput, combinationTest);
				postRemovalStringTest = postRemovalStringTest.replaceAll("\\s+","");
				//Parenthesis validation
				if(!minimalParenthesisSets.contains(postRemovalStringTest))
				{
					if(ParenthesisValidator.isValidParensGrouping(postRemovalStringTest))
					{
						minimalParenthesisSets.add(postRemovalStringTest);
					}
				}
				combinationTest.removeAll(rightRemovalCombinations.get(j));
			}
		}

		return minimalParenthesisSets;
	}

	private static String removeParensAtIndecies(String parenthesisInput, List<Integer> combinationTest)
	{
		StringBuilder outputString = new StringBuilder();
		outputString.append(parenthesisInput);

		Collections.sort(combinationTest);
		Integer removals = 0;

		for(Integer i : combinationTest)
		{
			outputString.deleteCharAt(i-removals);
			removals++;
		}

		return outputString.toString();
	}

	public static List<List<Integer>> combinationHelper(List<Integer> combinationChoices, Integer selectionsLeft, List<Integer> currentCombination)
	{
		if(selectionsLeft == 0 || combinationChoices.isEmpty())
		{
			return new ArrayList<>(new ArrayList<>(Arrays.asList(currentCombination)));
		}

		List<List<Integer>> localCombinations = new ArrayList<>();

		for(int i = 0; i < combinationChoices.size(); i++)
		{
			//create local selections possibilities list
			List<Integer> subCombination = new ArrayList<>();
			subCombination.addAll(combinationChoices);

			//create local selections
			List<Integer> subCurrent = new ArrayList<>();
			subCurrent.addAll(currentCombination);

			//select locally
			subCurrent.add(subCombination.remove(i));

			//return the recursive solution for this selection
			List<List<Integer>> subCombinations = combinationHelper(subCombination, selectionsLeft-1, subCurrent);
			//Add the recursive solution set for this selection to the greater return for the previous selection
			localCombinations.addAll(subCombinations);
		}

		return localCombinations;
	}
}
