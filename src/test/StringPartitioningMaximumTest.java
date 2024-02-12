package test;

import org.junit.jupiter.api.Test;
import examSources.StringPartitioningMaximum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringPartitioningMaximumTest
{

	@Test
	void partitionLabelsDefaultTestWIP()
	{
		//Input: S = "ababcbacadefegdehijhklij"
		//Output: [9,7,8]
		String s = "ababcbacadefegdehijhklij";
		List<Integer> returnValues = StringPartitioningMaximum.partitionLabels(s);
		assert returnValues.get(0).equals(9);
		assert returnValues.get(1).equals(7);
		assert returnValues.get(2).equals(8);
	}
}