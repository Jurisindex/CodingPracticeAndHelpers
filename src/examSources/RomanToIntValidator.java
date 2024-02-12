package examSources;

import java.util.HashMap;
import java.util.Map;

public class RomanToIntValidator
{
/*
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

	public static Map<Character, Integer> romanMappings = new HashMap<>();

	static {
		romanMappings.put('I',1);
		romanMappings.put('V',5);
		romanMappings.put('X',10);
		romanMappings.put('L',50);
		romanMappings.put('C',100);
		romanMappings.put('D',500);
		romanMappings.put('M',1000);
	}

	public static Integer romanToInt(String s)
	{
		//iterate through
		//greatestToSmallest
		//when we examine, check next in the string (if there's space)
		//If it's bigger, subtract A from B, and skip B as well. (++)

		Integer rollingSum = 0;

		for(int i = 0; i < s.length(); i++)
		{
			Integer currentValue = romanMappings.get(s.charAt(i));
			if((i+1) == s.length())
			{
				rollingSum += currentValue;
			}
			else
			{
				Integer nextValue = romanMappings.get(s.charAt(i+1));
				if(currentValue < nextValue) //it's a subtractionCase
				{
					Integer valueToAdd = nextValue - currentValue;
					rollingSum += valueToAdd;
					i++;
				}
				else	//no subtraction case needed
				{
					rollingSum += currentValue;
				}
			}
		}

		return rollingSum;
	}
}
