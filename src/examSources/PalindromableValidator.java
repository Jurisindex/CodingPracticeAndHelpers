package examSources;

import java.util.HashMap;
import java.util.Map;

public class PalindromableValidator
{
	/*
Your previous JavaScript content is preserved below:

function isPalindromable(input) {
  // TO DO: implement
  return false;
}

const inputs = [
  'aabb', // false => abba, baab => true
  'aabc', // false => false
  'aab', // false => aba => true
];

inputs.forEach(input => console.log(`${input}: ${isPalindromable(input)}`));
 */

	public static boolean isPalindromable(String input)
	{
		Map<Character, Integer> counters = new HashMap<Character,Integer>();

		//
		for(int i = 0; i < input.length(); i++)
		{
			Character c = input.charAt(i);
			if(counters.get(c) == null) //-1 is returned on a miss
			{
				counters.put(c, 1);
			}
			else
			{
				Integer characterCount = counters.get(c);
				counters.put(c, ++characterCount);
			}
		}

		boolean onlyOneOdd = false;

		for(Map.Entry e : counters.entrySet())
		{
			if((Integer)e.getValue() % 2 != 0) // it's odd
			{
				if(onlyOneOdd == false)
				{
					onlyOneOdd = true;  //make sure we only have one (1) odd count of a char
				}
				else
				{
					return false;
				}
			}
		}
		return true;
	}
}