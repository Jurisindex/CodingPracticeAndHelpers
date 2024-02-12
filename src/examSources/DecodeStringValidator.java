package examSources;

import helperClasses.Pair;

import java.util.Stack;

/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"



Constraints:

    1 <= s.length <= 30
    s consists of lowercase English letters, digits, and square brackets '[]'.
    s is guaranteed to be a valid input.
    All the integers in s are in the range [1, 300].
 */
public class DecodeStringValidator
{
    public static String decodeString(String s)
    {
        int bracketCharIndex = s.indexOf("[");
        int currentNumber = 0;
        String currentString = "";
        String stacklessString = "";
        boolean numberFinalized = false;
        if(bracketCharIndex == -1)
        {
            return s;
        }
        Stack<Pair<Integer, String>> stack = new Stack<>();

        int index = 0;

        while(index != s.length())
        {
            char c = s.charAt(index);

            if(c == ']')
            {
                String builtString = "";
                //Multiply the current string to create the full bit
                for(int i = 0; i < currentNumber; i++)
                {
                    builtString = builtString + currentString;
                }
                Pair<Integer, String> prevDecode = null;
                if(!stack.isEmpty())
                {
                    prevDecode = stack.pop();
                }
                else
                {
                    prevDecode = new Pair<>(0, "");
                    stacklessString = stacklessString + builtString;
                    builtString = "";
                }
                currentNumber = prevDecode.getKey();
                //Add the full bit to the previously popped stack. If no stack, set as currentString.
                currentString = prevDecode.getVal() + builtString;
                numberFinalized = false;
            }
            else
            if(c == '[')
            {
                numberFinalized = true;
            }
            else
            if(Character.isDigit(c))
            {
                if(currentNumber != 0)
                {
                    if(c == '0')
                    {
                        //throw warning
                    }
                    //Pack it up and put it on the stack
                    stack.push(new Pair<>(currentNumber, currentString));
                    currentString = "";

                    int nextOpenBracketIndex = index + s.substring(index).indexOf('[');
                    Integer fullNumber = Integer.parseInt(s.substring(index, nextOpenBracketIndex));
                    index = nextOpenBracketIndex;
                    currentNumber = fullNumber;
                }
                else
                {
                    int nextOpenBracketIndex = index + s.substring(index).indexOf('[');
                    Integer fullNumber = Integer.parseInt(s.substring(index, nextOpenBracketIndex));
                    index = nextOpenBracketIndex;
                    currentNumber = fullNumber;
                }
            }
            else
            {
                if(!stack.isEmpty() || currentNumber != 0)
                {
                    //Example path: 2[ab]c
                    currentString = currentString + c;
                }
                else
                {
                    //Example path: ab2[c]de
                    stacklessString = stacklessString + c;
                }
            }
            index++;
        }
        return stacklessString+currentString;
    }
}
