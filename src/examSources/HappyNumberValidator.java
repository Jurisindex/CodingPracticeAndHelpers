package examSources;

/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example:

Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1


 */

import java.util.ArrayList;
import java.util.List;

//Same as Lucky , but written from scratch without a stream.
public class HappyNumberValidator
{
	public static boolean isHappy(int n)
	{
		List<Integer> numbersPreviouslyVisited = new ArrayList<>();
		return isNumberHappyRecurse(n, numbersPreviouslyVisited);
	}

	private static boolean isNumberHappyRecurse(int n, List<Integer> numbersPreviouslyVisited)
	{
		if(n == 1)
		{
			return true;
		}

		if(numbersPreviouslyVisited.contains(n))
		{
			return false;
		}
		else
		{
			numbersPreviouslyVisited.add(n);
			List<Integer> splitNumbers = new ArrayList<>();
			//Split numbers into separate values
			while(n != 0)
			{
				int rollingOnes = n%10;
				splitNumbers.add(rollingOnes);
				n = n/10;
			}
			int result = 0;
			for(Integer i : splitNumbers)
			{
				result += i*i;
			}
			return isNumberHappyRecurse(result, numbersPreviouslyVisited);
		}
	}
}
