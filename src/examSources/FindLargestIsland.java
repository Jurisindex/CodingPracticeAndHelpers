package examSources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindLargestIsland
{
	public static Integer findLargestShape(boolean[][] pictureArray)
	{
		Integer longestChain = 0;
		Integer currentChain = 0;
		Integer numberOfIslands = 0;
		HashMap<Integer,List<Integer>> nodesExamined = new HashMap<>();

		for(int i = 0; i < pictureArray.length; i++)
		{
			for(int k = 0; k < pictureArray[i].length; k++)
			{
				//See if it's a black-pixel hit
				System.out.println("currently examining pixels: " + i + ", " + k);
				List<Integer> yVisited = nodesExamined.get(i);
				if(yVisited == null) yVisited = new ArrayList<>();
				boolean nodeVisited = yVisited.contains(k);
				if(pictureArray[i][k] == true && !nodeVisited)
				{
					HashMap<Integer,List<Integer>> nodesVisited = new HashMap<>();
					howLongIsTheChainConnectedToPointXY(pictureArray, i, k, nodesVisited);

					System.out.println("x values: " + nodesVisited.keySet());
					System.out.println("y values: " + nodesVisited.values());
					for(Integer key : nodesVisited.keySet())
					{
						currentChain += nodesVisited.get(key).size();
						for(Integer yCoord : nodesVisited.get(key))
						{
							addYCoordinateToXVisitedList(nodesExamined, key, yCoord);
						}
					}
					numberOfIslands++;
				}
				else
				{
					addYCoordinateToXVisitedList(nodesExamined, i, k);
				}

				if( currentChain > longestChain)
				{
					longestChain = currentChain;
				}
				currentChain = 0;
				//Record as we finish.
			}
		}
		System.out.println("Number of Islands, FYI: " + numberOfIslands);
		return longestChain;
	}

	public static void howLongIsTheChainConnectedToPointXY(boolean[][] pictureArray,
														   int x, int y,
														   HashMap<Integer,List<Integer>> nodesVisited)
	{
		//As we're finding out, make sure we don't loop in a square
		if(!addYCoordinateToXVisitedList(nodesVisited, x, y)) return;

		//Find out how long this chain goes
		if(x-1 >= 0 && pictureArray[x-1][y] == true )
		{
			howLongIsTheChainConnectedToPointXY(pictureArray, x-1,y, nodesVisited);
		}
		if(x+1 < pictureArray.length && pictureArray[x+1][y] == true)
		{
			howLongIsTheChainConnectedToPointXY(pictureArray, x+1,y, nodesVisited);
		}
		if(y-1 >= 0 && pictureArray[x][y-1] == true)
		{
			howLongIsTheChainConnectedToPointXY(pictureArray, x,y-1, nodesVisited);
		}
		if(y+1 < pictureArray[x].length && pictureArray[x][y+1] == true)
		{
			howLongIsTheChainConnectedToPointXY(pictureArray, x,y+1, nodesVisited);
		}
	}

	public static boolean addYCoordinateToXVisitedList(HashMap<Integer,List<Integer>> nodesVisited, int x, int y)
	{
		List<Integer> listRetrieved = nodesVisited.get(x);
		if(listRetrieved != null && listRetrieved.contains(y) == true)
		{
			return false;
		}
		else
		{
			if(listRetrieved == null)
			{
				listRetrieved = new ArrayList<Integer>();
			}
			listRetrieved.add(y);
			nodesVisited.put(x, listRetrieved);
			return true;
		}
	}
}


/*
Your previous Plain Text content is preserved below:


We are given a black and white picture with black shapes over a white background.
A shape is a continuous set of pixels. A shape can have holes, and is not constrained to any particular geometrical structure.
The size of a shape is the number of black pixels in it.
Find the size of the largest shape

X 0 X 0
0 0 X 0
0 X X 0
X X 0 X

 */