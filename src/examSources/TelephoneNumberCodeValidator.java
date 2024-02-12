package examSources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TelephoneNumberCodeValidator
{
	private static HashMap<Integer, List<String>> keyMap = new HashMap<>();

	public static List<String> returnAllPossibleCombinations(List<Integer> inputs)
	{
		if(inputs == null || inputs.isEmpty() || inputs.contains(1))
		{
			return new ArrayList<>();	//No I don't care to parse through the thing and remove 1s.
		}
		//In this case we have pre-population. Otherwise we have some input that we parse to populate this map.
		keyMap.put(2, Arrays.asList("a", "b", "c"));
		keyMap.put(3, Arrays.asList("d", "e", "f"));
		keyMap.put(4, Arrays.asList("g", "h", "i"));
		keyMap.put(5, Arrays.asList("j", "k", "l"));
		keyMap.put(6, Arrays.asList("m", "n", "o"));
		keyMap.put(7, Arrays.asList("p", "q", "r", "s"));
		keyMap.put(8, Arrays.asList("t", "u", "v"));
		keyMap.put(9, Arrays.asList("w", "x", "y", "z"));

		//This is the array that, given our input, this is our 2-D map for us to extract all possible combinations from.
		List<List<String>> possibilitiesArray = new ArrayList<>(inputs.size());

		//Parse the input and expand into possibilities.
		for(Integer i : inputs)
		{
			//Add to the 2D Map what we find from the input's mappings.
			possibilitiesArray.add(keyMap.get(i));
		}

		List<String> outputCombinations = new ArrayList<>();

		//Iterate through the first row, recursing down each way.
		for(int i = 0; i < possibilitiesArray.get(0).size(); i++)
		{
			//Start with a base
			String base = possibilitiesArray.get(0).get(i);
			//Store every combination of that base
			List<String> combinations = new ArrayList<>();
			//Recurse down the line
			combinations = returnCombinationsStartingWith(base, 1, possibilitiesArray);
			//Add the current index's list of combinations to the output.
			outputCombinations.addAll(combinations);
		}
		return outputCombinations;
	}

	private static List<String> returnCombinationsStartingWith(String base, int depth, List<List<String>> possibilitiesArray)
	{
		if(depth >= possibilitiesArray.size())
		{
			return Arrays.asList(base);
		}

		List<String> greaterCombinations = new ArrayList<>();
		for(int i = 0; i < possibilitiesArray.get(depth).size(); i++)
		{
			String newBase = base+possibilitiesArray.get(depth).get(i);
			List<String> combinations = new ArrayList<>();
			combinations = returnCombinationsStartingWith(newBase, depth+1, possibilitiesArray);
			greaterCombinations.addAll(combinations);
		}

		return greaterCombinations;
	}
}
