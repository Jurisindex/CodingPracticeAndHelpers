package examSources;

/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */

import helperClasses.Pair;

import java.util.ArrayList;
import java.util.List;

public class LongestPalindromeSubstringValidator
{
	static class PalindromePair extends Pair
	{
		public Integer startingIndex;

		public PalindromePair(Object key, Object value, Integer startingIndex)
		{
			super(key, value);
			this.startingIndex = startingIndex;
		}
	}

	public static String getLongestPalindromeSubstring(String s)
	{
		//Consider abdefghijm... etc.
		//Shortest palindrome: 1 string. Not really our concern.
		if(s.length() < 2)
		{
			return s;
		}
		String longestPalindrome = s.substring(0,1);	//just in case we don't find anything.

		List<PalindromePair> palindromePairList = new ArrayList<>(s.length()-1);

		//Pass through the string; Record ALL pairs. This makes n-1 data points. Record the 1st one's index as well.
		for(int i = 0; i < s.length()-1; i++)
		{
			PalindromePair pair = new PalindromePair(String.valueOf(s.charAt(i)),String.valueOf(s.charAt(i+1)),i);
			palindromePairList.add(pair);
		}

		List<Pair<Integer, Integer>> potentialPalindromeIndexList = new ArrayList<>(palindromePairList.size()/4);	//yeah 4 is random
		String doublesPair = "";
		//find any pair that might exist for some edge cases
		for(PalindromePair pair : palindromePairList)
		{
			if(pair.getKey().equals(pair.getVal()))
			{
				doublesPair = s.substring(pair.startingIndex,pair.startingIndex+2);
				break;
			}
		}

		//Search the pairs. Check if there exists a pair with key, value = value, key of another pair. Hmmmmm.... That's N^2'd tho.
		for(int i = 0; i < palindromePairList.size() - 1; i++)
		{
			PalindromePair pair1 = palindromePairList.get(i);
			for(int j = i+1; j < palindromePairList.size(); j++)
			{
				PalindromePair pair2 = palindromePairList.get(j);
				if(pair1.getKey().equals(pair2.getVal()) && pair1.getVal().equals(pair2.getKey()))
				{
					potentialPalindromeIndexList.add(new Pair<>(pair1.startingIndex, (pair2.startingIndex+1)));
				}
			}
		}

		//Okay. Anyway. For all matches found above^, sort by length descending, and return the first one who returns a positive in "is Palindromable"
		for(Pair palindromeIndecies : potentialPalindromeIndexList)
		{
			String palindrome = s.substring((Integer) palindromeIndecies.getKey(), (Integer) palindromeIndecies.getVal()+1);
			if(isPalindrome(palindrome) && palindrome.length() > longestPalindrome.length())
			{
				longestPalindrome = palindrome;
			}
		}

		if(longestPalindrome.length() == 1 && doublesPair.length() > 1)
		{
			return doublesPair;
		}

		return longestPalindrome;
	}

	public static boolean isPalindrome(String s)
	{
		int n = s.length();
		for (int i = 0; i < (n/2); ++i)
		{
			if (s.charAt(i) != s.charAt(n - i - 1))
			{
				return false;
			}
		}

		return true;
	}
}
