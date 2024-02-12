package examSources;

/*
Given a list of strings dict where all the strings are of the same length.
Return true if there are 2 strings that only differ by 1 character in the same index, otherwise return false.

Example 1:

Input: dict = ["abcd","acbd", "aacd"]
Output: true
Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.

Example 2:

Input: dict = ["ab","cd","yz"]
Output: false

Example 3:

Input: dict = ["abcd","cccc","abyd","abab"]
Output: true

Constraints:

    The number of characters in dict <= 105
    dict[i].length == dict[j].length
    dict[i] should be unique.
    dict[i] contains only lowercase English letters.

Follow up: Could you solve this problem in O(n * m) where n is the length of dict and m is the length of each string.
 */

import java.util.HashSet;
import java.util.Set;

public class StringsDifferByOneCharacterValidator
{
    public static boolean differByOne(String[] dict)
    {
        if (dict == null || dict.length < 2)
        {
            return false; // Handling null input or insufficient number of strings
        }
        //For each index (length of first string):
            //Remove a character from each string
            //Add the strings to a set, is there a match
        Set<String> stringSet = new HashSet<>();
        for(int i = 0; i < dict[0].length(); i++)
        {
            stringSet.clear();
            for(int s = 0; s < dict.length; s++)
            {
                String cutString = dict[s].substring(0, i) + dict[s].substring(i + 1);
                if(!stringSet.add(cutString))
                    return true;
            }
        }
        return false;
    }
}
