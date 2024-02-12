package examSources;

import helperClasses.Pair;

import java.util.ArrayList;
import java.util.List;

public class Find2dWords
{
//34 of 36 test cases pass. Time limit issue. Honestly; don't care. :^)

	/*
	Given a 2D board and a list of words from the dictionary, find all words in the board.

	Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

	Example:

	Input:
	board = [
		['o','a','a','n'],
		['e','t','a','e'],
		['i','h','k','r'],
		['i','f','l','v']
		]
	words = ["oath","pea","eat","rain"]

	Output: ["eat","oath"]*/

	public static List<String> findGivenWordsInMap(char[][] charMap, List<String> dictionary)
	{
		//Figure out what words I've left to find
		List<String> foundWords = new ArrayList<>();
		List<String> unfoundWords = dictionary;
		//Of those left, what are the first letters
		List<Character> firstLetters = getFirstLetters(unfoundWords);
		//Are there any hits?
			//iterate the charMap
		for(int i = 0; i < charMap.length; i++)
		{
			for(int j = 0; j < charMap[0].length; j++)
			{
				char letterInMap = charMap[i][j];
				//check against the firstLetters list
				if(firstLetters.contains(letterInMap))
				{
					for(int w = 0; w < unfoundWords.size(); w++)
					{
						String word = unfoundWords.get(w);
						if(word.charAt(0) == letterInMap)
						{
							//If a hit, enter recursive checking, making sure not to repeat overlap
							List<Pair<Integer,Integer>> visitedNodes = new ArrayList<>();

							Boolean found = wordFoundInMap(charMap, i, j, word, visitedNodes, 0);

							if(found)
							{
								//If a word is found, add to found, remove from unfound, recalculate firstLetters. If unfound empty, exit
								foundWords.add(word);
								unfoundWords.remove(word);
								w--;
								firstLetters = getFirstLetters(unfoundWords);
								if(unfoundWords.isEmpty())
								{
									return foundWords;
								}
							}
						}
					}

				}
			}
		}
		return foundWords;
	}

	private static Boolean wordFoundInMap(char[][] charMap, int x, int y, String word, List<Pair<Integer, Integer>> visitedNodes, Integer charsFound)
	{
		//If already visited, don't go down that path.
		if(!addXYCoordinateToVisitedList(visitedNodes, x, y)) return false;

		charsFound++;
		if(charsFound == word.length())
		{
			return true;
		}

		char nextCharToFind = word.charAt(charsFound);

		//check up
		Pair<Integer,Integer> nextCoords = new Pair<>(x-1, y);
		if(x-1 >= 0 && charMap[x-1][y] == nextCharToFind && !visitedNodes.contains(nextCoords))
		{
			if(wordFoundInMap(charMap, x-1,y, word, visitedNodes, charsFound)) return true;
			visitedNodes.remove(nextCoords);
		}
		//check down
		nextCoords = new Pair<>(x+1, y);
		if(x+1 < charMap.length && charMap[x+1][y] == nextCharToFind && !visitedNodes.contains(nextCoords))
		{
			if(wordFoundInMap(charMap, x+1,y, word, visitedNodes, charsFound)) return true;
			visitedNodes.remove(nextCoords);
		}
		//check left
		nextCoords = new Pair<>(x, y-1);
		if(y-1 >= 0 && charMap[x][y-1] == nextCharToFind && !visitedNodes.contains(nextCoords))
		{
			if(wordFoundInMap(charMap, x,y-1, word, visitedNodes, charsFound)) return true;
			visitedNodes.remove(nextCoords);
		}
		//check right
		nextCoords = new Pair<>(x, y+1);
		if(y+1 < charMap[x].length && charMap[x][y+1] == nextCharToFind && !visitedNodes.contains(nextCoords))
		{
			if(wordFoundInMap(charMap, x,y+1, word, visitedNodes, charsFound)) return true;
			visitedNodes.remove(nextCoords);
		}

		return false;
	}

	private static boolean addXYCoordinateToVisitedList(List<Pair<Integer, Integer>> visitedNodes, int x, int y)
	{
		Pair<Integer, Integer> nodeVisited = new Pair(x,y);
		if(visitedNodes != null && visitedNodes.contains(nodeVisited))
		{
			return false;
		}
		else
		{
			visitedNodes.add(nodeVisited);
			return true;
		}
	}

	private static List<Character> getFirstLetters(List<String> unfoundWords)
	{
		List<Character> firstLetters = new ArrayList<>(unfoundWords.size());
		for (String word: unfoundWords)
		{
			firstLetters.add(word.charAt(0));
		}

		return firstLetters;
	}
}
