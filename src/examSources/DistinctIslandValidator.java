package examSources;

/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally
(horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be
translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.



 */

import helperClasses.Island;

import java.util.ArrayList;
import java.util.List;

public class DistinctIslandValidator
{
	public static int numDistinctIslands(int[][] grid)
	{
		//annoying-case check
		if(grid.length == 0)	return 0;

		//Initialize an X by Y grid that is all 0s. This represents our traversal through it.

		List<List<Integer>> nodesSeen = twoDimensionalArray(grid.length, grid[0].length);

		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(nodesSeen.get(i).get(j).equals(0))	//We have yet to traverse this.
				{
					//Determine if it's 1 or 0.
					Integer node = grid[i][j];
					//If 0, continue;
					if(node.equals(0))
					{
						continue;
					}
					//If 1, enter subroutine to fully explore the island
					else
					{
						List<List<Integer>> nodesVisitedLocaly = twoDimensionalArray(nodesSeen.size(), nodesSeen.get(0).size());
						howLongIsTheChainConnectedToPointXY(grid, i, j, nodesVisitedLocaly);

						for(List<Integer> list : nodesVisitedLocaly)
						{
							for(Integer value : list)
							{
								System.out.print(value);
							}
							System.out.println();
						}
					}
				}
			}
		}
		return 0;
	}

	private static void howLongIsTheChainConnectedToPointXY(int[][] grid, int x, int y, List<List<Integer>> nodesVisitedLocaly)
	{
		//As we're finding out, make sure we don't loop in a square
		if(!addYCoordinateToXVisitedList(nodesVisitedLocaly, x, y)) return;

		//Find out how long this chain goes
		if(x-1 >= 0 && grid[x-1][y] == 1 )
		{
			howLongIsTheChainConnectedToPointXY(grid, x-1,y, nodesVisitedLocaly);
		}
		if(x+1 < grid.length && grid[x+1][y] == 1)
		{
			howLongIsTheChainConnectedToPointXY(grid, x+1,y, nodesVisitedLocaly);
		}
		if(y-1 >= 0 && grid[x][y-1] == 1)
		{
			howLongIsTheChainConnectedToPointXY(grid, x,y-1, nodesVisitedLocaly);
		}
		if(y+1 < grid[x].length && grid[x][y+1] == 1)
		{
			howLongIsTheChainConnectedToPointXY(grid, x,y+1, nodesVisitedLocaly);
		}
	}

	private static boolean addYCoordinateToXVisitedList(List<List<Integer>> nodesVisitedLocaly, int x, int y)
	{
		if(nodesVisitedLocaly.get(x).get(y) == 0)
		{
			return false;
		}
		else
		{
			nodesVisitedLocaly.get(x).set(y, 1);
			return true;
		}
	}

	private static List<List<Integer>> twoDimensionalArray(int width, int length)
	{
		List<List<Integer>> result = new ArrayList<>(width);
		for(List<Integer> column: result)
		{
			for(int i = 0; i < length; i++)
			{
				column.add(0);
			}
		}
		return result;
	}
}
