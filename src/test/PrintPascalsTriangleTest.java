package test;

import org.junit.jupiter.api.Test;
import examSources.PrintPascalsTriangle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintPascalsTriangleTest
{

	@Test
	void pascalsTriangleForNRows()
	{
		List<List<Integer>> results = PrintPascalsTriangle.pascalsTriangleForNRows(6);
		for(List<Integer> res : results)
		{
			for(Integer i : res)
			{
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}