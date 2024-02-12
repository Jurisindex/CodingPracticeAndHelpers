package examSources;

import java.util.ArrayList;
import java.util.List;

/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:
Input: 121
Output: true

Example 2:
Input: -121
Output: false

Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:
Input: 10
Output: false

Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Follow up:
Could you solve it without converting the integer to a string?
 */
public class IntegerPalindromeValidator
{
	public static boolean isIntegerPalindrome(int a)
	{
		if(a == 0) return true;	//special case
		//a mod 10 being 0 necessitates a leading 0. Not possible. Also avoids a logic error at while(a > 0)
		if (a < 0 || a%10 == 0) return false;
		List<Integer> reverseIntegerList = new ArrayList<>();

		//parse the int into a data structure
		while(a > 0)
		{
			reverseIntegerList.add(a%10);
			a = a/10;
		}

		while(reverseIntegerList.size() > 1)
		{
			//check the first digit
			int first = reverseIntegerList.remove(0);
			//check the last digit
			int last = reverseIntegerList.remove(reverseIntegerList.size()-1);
			//check them matching
			if(first != last) return false;
			//repeat
		}
		//if size <=1, return true

		return true;
	}
}
