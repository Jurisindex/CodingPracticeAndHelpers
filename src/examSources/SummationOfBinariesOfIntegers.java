package examSources;

import helperClasses.CommonHelperMethods;

import java.util.ArrayList;
import java.util.List;

public class SummationOfBinariesOfIntegers
{
	public static Integer summationOfInputBinariesAsInteger(List<Integer> inputs)
	{
		List<String> binaryRepresentations = new ArrayList<>();
		for(Integer i : inputs)
		{
			binaryRepresentations.add(CommonHelperMethods.integerToBinary(i));
		}

		String bitsByReverseOrder = "";
		for(String s : binaryRepresentations)
		{
			bitsByReverseOrder+= CommonHelperMethods.reverseString(s);
		}
		bitsByReverseOrder = CommonHelperMethods.reverseString(bitsByReverseOrder);

		Integer returnValue = Integer.parseInt(bitsByReverseOrder, 2);
		return returnValue;
	}
}
