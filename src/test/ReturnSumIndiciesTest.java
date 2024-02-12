package test;

import org.junit.jupiter.api.Test;
import examSources.ReturnSumIndicies;

import static org.junit.jupiter.api.Assertions.*;

class ReturnSumIndiciesTest
{
	@Test
	void returnCorrectIndeciesEasyPositiveCase()
	{
		int[] input = new int[] {1,2,3,4,5,6};
		int[] output = ReturnSumIndicies.twoIndiciesToEqualTarget(input,11);
		assert output[0] == 4;
		assert output[1] == 5;
	}

	@Test
	void indeciesTestHalfOfTarget()
	{
		int[] input = new int[] {1,2,3,4,5,75,90,91,5,95};
		int[] output = ReturnSumIndicies.twoIndiciesToEqualTarget(input,10);
		System.out.println(output[0]);
		System.out.println(output[1]);
		assert output[0] == 4;
		assert output[1] == 8;
	}
}