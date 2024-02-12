package examSources;

import java.util.ArrayList;
import java.util.List;

/*
1402. Reducing Dishes
Hard

291

63

Add to List

Share
A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.

Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level  i.e.  time[i]*satisfaction[i]

Return the maximum sum of Like-time coefficient that the chef can obtain after dishes preparation.

Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.



Example 1:

Input: satisfaction = [-1,-8,0,5,-9]
Output: 14
Explanation: After Removing the second and last dish, the maximum total Like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14). Each dish is prepared in one unit of time.
Example 2:

Input: satisfaction = [4,3,2]
Output: 20
Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)
Example 3:

Input: satisfaction = [-1,-4,-5]
Output: 0
Explanation: People don't like the dishes. No dish is prepared.
Example 4:

Input: satisfaction = [-2,5,-1,0,3,-3]
Output: 35


Constraints:

n == satisfaction.length
1 <= n <= 500
-10^3 <= satisfaction[i] <= 10^3
 */

public class ReducingDishesValidator
{
	public static int maxSatisfaction(int[] satisfaction)
	{
		//First you sort all the non-negative numbers in increasing order.
		List<Integer> satisfactionList = new ArrayList<>();
		for(int sat : satisfaction)
		{
			satisfactionList.add(sat);
		}

		satisfactionList.sort(Integer::compareTo);

		Integer positiveSum = 0;
		Integer smallestNegativeIndex = -1;
		List<Integer> dishesToBeCooked = new ArrayList<>();

		for(int i = 0; i < satisfactionList.size(); i++)
		{
			Integer currentNum = satisfactionList.get(i);
			if(currentNum < 0)
			{
				smallestNegativeIndex = i;
			}
			else
			{
				positiveSum += currentNum;
				dishesToBeCooked.add(currentNum);
			}
		}

		//Then, you keep try the adding of the negative numbers in,
		//as long as adding them does not decrease the score

		if(smallestNegativeIndex < 0)
		{
			//Skip the next while loop. All the numbers have been added.
			positiveSum = -1;
		}
		else
		{
			Integer currentNegative = satisfactionList.get(smallestNegativeIndex);
			positiveSum += currentNegative;
			if(positiveSum > 0)
			{
				dishesToBeCooked.add(0, currentNegative);
			}
			smallestNegativeIndex -= 1;
		}
		while(positiveSum > 0)
		{
			if(smallestNegativeIndex < 0)
			{
				//All the numbers have been added.
				break;
			}
			else
			{
				Integer currentNegative = satisfactionList.get(smallestNegativeIndex);
				positiveSum += currentNegative;
				smallestNegativeIndex -= 1;
				if(positiveSum > 0)
				{
					dishesToBeCooked.add(0, currentNegative);
				}
			}
		}

		Integer returnSum = 0;
		for(int i = 0; i < dishesToBeCooked.size(); i++)
		{
			returnSum += ((i+1)*dishesToBeCooked.get(i));
		}

		return returnSum;
	}
}
