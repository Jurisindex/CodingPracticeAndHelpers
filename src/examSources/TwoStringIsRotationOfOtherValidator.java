package examSources;

import java.util.ArrayList;
import java.util.List;

/*
	waterbottle can be rotated to :	terbottlewa => return true

	title : tleti

	Restriction: only 1 call to isSubstring
 */
public class TwoStringIsRotationOfOtherValidator
{
	public static boolean stringIsRotationOfOther(String normal, String rotated)
	{
		//edge-case : check string length is the same first.
		if(normal.length() != rotated.length())
		{
			return false;
		}

		//String rotatedLeadingCharacters = rotated.substring(0, rotated.length()/2);
		//if(normal.contains(rotatedLeadingCharacters));

		//find the first character of string 1
		char firstCharOfNormalString = normal.charAt(0);
		//find EACH character occurence of that character in string 2
		List<Integer> characterLocations = new ArrayList<>();
		for(int i = 0; i < rotated.length(); i++)
		{
			if(firstCharOfNormalString == rotated.charAt(i))
			{
				characterLocations.add(i);
			}
		}

		boolean match;
		for(int occurence = 0; occurence < characterLocations.size(); occurence++)
		{
			Integer startRotatedLocation = characterLocations.get(occurence);
			match = isStringRotationStartingAtIndex(normal, rotated, startRotatedLocation);
			if(match)
			{
				return true;
			}
		}
		//if all matching in the meanwhile, a match
		return false;
	}

	public static boolean isStringRotationStartingAtIndex(String normal, String rotated, Integer startingIndex)
	{
		//follow each indecies upwards, looping if necessary
		for(int i = 0; i < normal.length(); i++)
		{
			Integer rotatedIndexToScan = startingIndex+i;
			if(rotatedIndexToScan >= normal.length())
			{
				rotatedIndexToScan -= normal.length();
			}
			char normalChar = normal.charAt(i);
			char rotatedChar = rotated.charAt(rotatedIndexToScan);
			if(normalChar != rotatedChar)
			{
				return false;
			}
		}

		return true;
	}
}
