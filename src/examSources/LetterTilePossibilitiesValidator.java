package examSources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
    You have n  tiles, where each tile has one letter tiles[i] printed on it.

    Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.



    Example 1:

    Input: tiles = "AAB"
    Output: 8
    Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

    Example 2:

    Input: tiles = "AAABBC"
    Output: 188

    Example 3:

    Input: tiles = "V"
    Output: 1



    Constraints:

        1 <= tiles.length <= 7
        tiles consists of uppercase English letters.


 */
public class LetterTilePossibilitiesValidator
{
    public static int numTilePossibilities(String tiles)
    {
        Map<Character, Integer> tilemap = new HashMap<>();
        for(char c : tiles.toCharArray())
        {
            tilemap.put(c, tilemap.getOrDefault(c,0)+1);
        }
        Set<String> stringCombinations = new HashSet<>();
        backtrack(tilemap, "", stringCombinations);
        return stringCombinations.size();
    }

    private static void backtrack(Map<Character, Integer> tilemap, String s, Set<String> stringCombinations)
    {
        for(char c : tilemap.keySet())
        {
            int count = tilemap.get(c);
            if(count == 0)
            {
                continue;
            }
            tilemap.put(c, count-1);
            String combination = s + c;
            stringCombinations.add(combination);
            backtrack(tilemap, combination, stringCombinations);
            tilemap.put(c, count);
        }
    }
}
