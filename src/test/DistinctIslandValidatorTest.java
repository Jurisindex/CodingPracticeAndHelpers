package test;

import org.junit.jupiter.api.Test;
import examSources.DistinctIslandValidator;

import static org.junit.jupiter.api.Assertions.*;

class DistinctIslandValidatorTest
{

	@Test
	void numDistinctIslands()
	{
		int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
		System.out.println(DistinctIslandValidator.numDistinctIslands(grid));
	}
}