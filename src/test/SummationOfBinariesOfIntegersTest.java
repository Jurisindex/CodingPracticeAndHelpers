package test;

import org.junit.jupiter.api.Test;
import examSources.SummationOfBinariesOfIntegers;

import java.util.ArrayList;
import java.util.List;

class SummationOfBinariesOfIntegersTest
{

	@Test
	void summationOfInputBinariesAsInteger()
	{
		List<Integer> integers = new ArrayList<>();
		integers.add(24);
		integers.add(85);
		integers.add(0);

		Integer summation = SummationOfBinariesOfIntegers.summationOfInputBinariesAsInteger(integers);
		assert (summation.equals(21784));
	}
}