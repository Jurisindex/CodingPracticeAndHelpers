package helperClasses;

import java.util.List;

public class Island
{
	//A visual grid that is a super-rectangle of the island. Represented in 1 and 0.
	List<List<Integer>> subGrid;
	Integer largestX;
	Integer largestY;
	Integer smallestX;
	Integer smallestY;

	public Island(List<List<Integer>> subGrid, Integer largestX, Integer largestY)
	{
		this.subGrid = subGrid;
		this.largestX = largestX;
		this.smallestX = largestX - subGrid.size();
		this.largestY = largestY;
		this.smallestY = largestY - subGrid.get(0).size();
	}

	public boolean equals(Island island)
	{
		//If the grid X and Y length don't match, just return false;
		if(island.subGrid.size() != this.subGrid.size())	return false;
		if(island.subGrid.get(0).size() != this.subGrid.get(0).size())	return false;

		for(int i = 0; i < subGrid.size(); i++)
		{
			for(int j = 0; j < subGrid.get(0).size(); j++)
			{
				if(island.subGrid.get(i).get(j) != this.subGrid.get(i).get(j))
				{
					return false;
				}
			}
		}
		return true;
	}
}
