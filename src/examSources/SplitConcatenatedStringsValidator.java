package examSources;

import helperClasses.CommonHelperMethods;

import java.util.ArrayList;
import java.util.List;

/*
    You are given an array of strings strs. You could concatenate these strings together into a loop, where for each string, you could choose to reverse it or not. Among all the possible loops

    Return the lexicographically largest string after cutting the loop, which will make the looped string into a regular one.

    Specifically, to find the lexicographically largest string, you need to experience two phases:

        Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
        Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.

    And your job is to find the lexicographically largest one among all the possible regular strings.

    Example 1:
    Input: strs = ["abc","xyz"]
    Output: "zyxcba"
    Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", where '-' represents the looped status.
    The answer string came from the fourth looped one, where you could cut from the middle character 'a' and get "zyxcba".

    Example 2:
    Input: strs = ["abc"]
    Output: "cba"

    Constraints:

        1 <= strs.length <= 1000
        1 <= strs[i].length <= 1000
        1 <= sum(strs[i].length) <= 1000
        strs[i] consists of lowercase English letters.
 */
public class SplitConcatenatedStringsValidator
{
    public static String splitLoopedString(String[] strs)
    {
        //["abc","xyz","efg","xyz"] => "xyzgfezyxcba
        //For each string, find the lexigraphically largest version (reversed or not)
        //After that, compare to the current "largestLexigraphicalString"
        //Then parse through the array, making 2 string "second half" and "first half".
        //We'll make the second half first. For when we reach the "largestLexigraphicalString", we start the "first half"
        //return the two concatenated strings
        String largestLexigraphicalString = "";
        int occurrences = 0;

        //max log(n) operation
        while(occurrences != 1)
        {
            //repeat while occurrences != 1
            //amoratized/max log(n) while loop => n
            for(int i = 0; i < strs.length; i++)
            {
                String current = strs[i];
                //k operation where k is avg length of string
                String currentReversed = CommonHelperMethods.reverseString(current);
                if(current.compareTo(currentReversed) < 0)
                {
                    current = currentReversed;
                    strs[i] = current;
                }
                //returns the value 0 if the argument string is equal to this string;
                // a value less than 0 if this string is lexicographically less than the string argument;
                // and a value greater than 0 if this string is lexicographically greater than the string argument.
                int lexigraph = current.compareTo(largestLexigraphicalString);
                if(lexigraph == 0)
                {
                    occurrences++;
                }
                else if (lexigraph > 0)
                {
                    largestLexigraphicalString = current;
                    occurrences = 1;
                }
            }
            //if occurrences != 1, iterate through the array again
            //when you find the BigWord, take it AND its subsequent neighbor (if any. If last index, take first index)
            //  and concatenate them. Then modify strs[], and proceed again.
            if(occurrences != 1)
            {
                List<String> newStrs = new ArrayList<>();
                for(int i = 0; i < strs.length; i++)
                {
                    String current = strs[i];
                    if(current.equals(largestLexigraphicalString))
                    {
                        int indexToAdd = i+1;
                        //["efg","xyz","abc","xyz"] => "xyzgfezyxcba | test case
                        if(i == strs.length-1)
                        {
                            indexToAdd = 0;
                            newStrs.remove(0);
                        }
                        current += strs[indexToAdd];
                        i++;
                    }
                    newStrs.add(current);
                }
                occurrences = 0;
                strs = newStrs.toArray(new String[0]);
            }
        }

        StringBuilder firstHalf = new StringBuilder();
        StringBuilder secondHalf = new StringBuilder();
        for(int i = 0; i < strs.length; i++)
        {
            String current = strs[i];
            if(current.equals(largestLexigraphicalString))
            {
                firstHalf.append(largestLexigraphicalString);
            }
            else if(firstHalf.isEmpty())
            {
                secondHalf.append(current);
            }
            else //We have found the largest and already partly parsed it
            {
                firstHalf.append(current);
            }
        }
        return firstHalf.append(secondHalf).toString();
    }
}
