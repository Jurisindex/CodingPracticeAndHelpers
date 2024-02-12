package examSources;

/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.

		You may assume that each input would have exactly one solution, and you may not use the same element twice.

		Example:

		Given nums = [2, 7, 11, 15], target = 9,

		Because nums[0] + nums[1] = 2 + 7 = 9,
		return [0, 1].
*/

import java.util.ArrayList;
public class ReturnSumIndicies
{
	public static int[] twoIndiciesToEqualTarget(int[] numbers, int target)
	{
		int[] resultSet = new int[2];
		boolean doubleHit = false;
		Integer operandIndex1 = -1;
		Integer operandIndex2 = -1;

		//logic
		ArrayList<Integer> arrayOfNumbers = new ArrayList<>();
		ArrayList<Integer> arrayOfDifferences = new ArrayList<>();

		populateArraysOfNumbersAndDifferences(numbers, target, arrayOfNumbers, arrayOfDifferences);

		//iterate arrayList1.
		//If arrayList1.contains(arrayList2[i])
		//If arrayList1[i] == arrayList2[i]
		for(int i = 0; i < arrayOfNumbers.size(); i++)
		{
			int differenceValue = arrayOfDifferences.get(i);
			if(arrayOfNumbers.contains(differenceValue))
			{
				boolean isHalfOfTarget = ((differenceValue*2) == target);
				if(isHalfOfTarget)
				{
					//check if it's the half-of special case
					if(doubleHit)
					{
						operandIndex2 = i;
						break;
					}
					else
					{
						operandIndex1 = i;
						doubleHit = true;
					}
				}
				else
				{
					//no special case. We have a hit.
					operandIndex1 = i;
					operandIndex2 = arrayOfNumbers.indexOf(differenceValue);
					break;
				}
			}
		}

		resultSet[0] = operandIndex1;
		resultSet[1] = operandIndex2;
		return resultSet;
	}

	private static void populateArraysOfNumbersAndDifferences(int[] numbers, int target, ArrayList<Integer> arrayOfNumbers, ArrayList<Integer> arrayOfDifferences)
	{
		//iterate the array
		//add the element to an arrayList
		//add the difference between element and the target to arrayList2
		for (int num : numbers)
		{
			arrayOfNumbers.add(num);
			arrayOfDifferences.add(target - num);
		}
	}
}
