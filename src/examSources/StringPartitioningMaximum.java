package examSources;

/*
	A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

	Example 1:
	Input: S = "ababcbacadefegdehijhklij"
	Output: [9,7,8]
	Explanation:
	The partition is "ababcbaca", "defegde", "hijhklij".
	This is a partition so that each letter appears in at most one part.
	A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
	Note:

	S will have length in range [1, 500].
	S will consist of lowercase letters ('a' to 'z') only.
*/

import java.util.ArrayList;
import java.util.List;

//wip: (Does not pass the above test [yet])
public class StringPartitioningMaximum {
	public static List<Integer> partitionLabels(String S)
	{
		List<String> partitionedStrings = new ArrayList<>();
		partitionedStrings.add(S);
		partitionFurther(partitionedStrings);

		List<Integer> sizes = new ArrayList<>();

		for(String s : partitionedStrings)
		{
			sizes.add(s.length());
		}

		return sizes;
	}

	public static List<String> partitionFurther(List<String> inputStrings)
	{
		//take the first character of the last String in the list
		String lastString = inputStrings.remove(inputStrings.size() - 1);
		String replacementStringA = "";
		String replacementStringB = "";
		System.out.println("current last string, " + lastString);
		Character leadingChar = lastString.charAt(0);
		//Find out where its last repetition is
		Integer repeatingIndex = findLastOccurrenceOfCharacterInString(leadingChar, lastString);
		if(repeatingIndex.equals(0))
		{
			//If no character repetition exists,
			//just substring that 1 char split up into itself
			replacementStringA = String.valueOf(leadingChar);
			replacementStringB = lastString.substring(1);
		}
		else
		{
			//We have to do MORE work here.
			//Find where the LAST place every character in replacementStringA exists.
			//The last one
			int tempStore = repeatingIndex;
			replacementStringA = lastString.substring(0,repeatingIndex+1);
			replacementStringB = lastString.substring(repeatingIndex+1);
			Integer lastPlaceOfReplacement = 0;
			for(int i = 0; i < replacementStringA.length(); i++)
			{
				Character c = replacementStringA.charAt(i);
				repeatingIndex = findLastOccurrenceOfCharacterInString(c, replacementStringB);

				if(repeatingIndex > lastPlaceOfReplacement)
				{
					lastPlaceOfReplacement = repeatingIndex;
				}
			}

			if(lastPlaceOfReplacement > 0)
			{
				replacementStringA = lastString.substring(0,tempStore+lastPlaceOfReplacement+1);
				replacementStringB = lastString.substring(tempStore+lastPlaceOfReplacement+1);
			}
		}
		System.out.println("replacement string A, " + replacementStringA);
		System.out.println("replacement string A, " + replacementStringB);

		//repeat till the last String is not modified.
		if(replacementStringB.equals(""))
		{
			return inputStrings;
		}
		else
		{
			inputStrings.add(replacementStringA);
			inputStrings.add(replacementStringB);
			return partitionFurther(inputStrings);
		}
	}


	public static Integer findLastOccurrenceOfCharacterInString(Character c, String s)
	{
		Integer repeatingIndex = 0;
		for(int i = 0; i < s.length(); i++)
		{
			if(c.equals(s.charAt(i)))
			{
				repeatingIndex = i;
			}
		}
		return repeatingIndex;
	}
}