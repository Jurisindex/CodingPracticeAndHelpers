package examSources;

/*
	1
	11
	121
	1331
	14641
 */

import java.util.ArrayList;
import java.util.List;

public class PrintPascalsTriangle
{
	public static List<List<Integer>> pascalsTriangleForNRows(Integer n)
	{
		if(n <= 0) return null;

		//Setup of first row
		List<List<Integer>> pascalsTriangle = new ArrayList<>();
		List<Integer> row1 = new ArrayList<>();
		row1.add(1);
		pascalsTriangle.add(row1);

		for(int i = 1; i < n; i++)	//for each row we have to fill, from [1-n), we already filled in the 0th row
		{
			//Create a new row, start with a 1
			List<Integer> newRow = new ArrayList<>();
			newRow.add(1);

			//Evaluate addition of rolling 2 numbers of previous Row
			populateMiddleNumbersOfNewRow(newRow, pascalsTriangle.get(pascalsTriangle.size()-1));

			//End with a 1
			newRow.add(1);
			pascalsTriangle.add(newRow);
		}

		return pascalsTriangle;
	}

	private static void populateMiddleNumbersOfNewRow(List<Integer> newRow, List<Integer> previousRow)
	{
		for(int i = 0; i < previousRow.size()-1; i++)
		{
			Integer leftNumber = previousRow.get(i);
			Integer rightNumber = previousRow.get(i+1);
			newRow.add(leftNumber + rightNumber);
		}
	}
}
