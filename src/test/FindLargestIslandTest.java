package test;

import org.junit.jupiter.api.Test;
import examSources.FindLargestIsland;

import static org.junit.jupiter.api.Assertions.*;

class FindLargestIslandTest
{

	@Test
	void findLargestShapeSampleTest()
	{
		boolean[][] arrayOfPicture = new boolean[4][4];
		arrayOfPicture[0][0] = true;
		arrayOfPicture[0][2] = true;
		arrayOfPicture[1][2] = true;
		arrayOfPicture[2][2] = true;
		arrayOfPicture[3][0] = true;
		arrayOfPicture[2][1] = true;
		arrayOfPicture[3][1] = true;
		arrayOfPicture[3][3] = true;
		Integer result = FindLargestIsland.findLargestShape(arrayOfPicture);
		assert result == 6;
		System.out.println("Success");
	}
}