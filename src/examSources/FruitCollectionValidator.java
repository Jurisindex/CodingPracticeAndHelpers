package examSources;

import java.util.List;

/*
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?



Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.


Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length
*/

public class FruitCollectionValidator
{
	public static Integer maxFruitsCollectable(List<Integer> treeList)
	{
		if(treeList.size() <= 2)
		{
			return treeList.size();
		}

		Integer type1 = treeList.get(0);
		Integer type2 = null;

		//Combos are needed for cases like: [0,1,6,6,4,4,6]
		Integer comboNumber;
		Integer comboMeter = 0;

		Integer currentChain = 1;
		Integer maxChain = 0;

		while(type2 == null)
		{
			if(currentChain.equals(treeList.size()))
			{
				return currentChain;
			}
			if(!type1.equals(treeList.get(currentChain)))
			{
				type2 = treeList.get(currentChain);
			}
			currentChain++;
		}

		comboNumber = type2;
		comboMeter = 1;

		maxChain = currentChain;

		for(int i = currentChain; i < treeList.size(); i++)
		{
			Integer currentFruitTree = treeList.get(i);
			if(currentFruitTree.equals(type2))
			{
				comboMeter++;
				currentChain++;
			}
			else if(currentFruitTree.equals(type1))
			{
				comboNumber = type1;
				type1 = type2;
				type2 = comboNumber;
				//type2 is guaranteed to be the latest hit number
				currentChain++;
				comboMeter = 1;
			}
			else
			{
				if(currentChain > maxChain)
				{
					maxChain = currentChain;
				}
				//first two should be filled in.
				currentChain = 1 + comboMeter;
				type1 = type2;
				type2 = currentFruitTree;
				comboNumber = type2;
				comboMeter = 1;
			}
		}
		if(currentChain > maxChain)
		{
			maxChain = currentChain;
		}

		return maxChain;
	}
}