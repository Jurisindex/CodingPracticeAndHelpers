package helperClasses;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonHelperMethods
{
	/***
	 *     +-+-+-+-+-+-+-+-+-+-+-+-+ +-+-+ +-+-+-+ +-+-+-+-+-+
	 *     |I|n|i|t|i|a|l|i|z|e|r|.| |D|o| |N|o|t| |A|l|t|e|r|
	 *     +-+-+-+-+-+-+-+-+-+-+-+-+ +-+-+ +-+-+-+ +-+-+-+-+-+
	 */
	private CommonHelperMethods(){}

	private static class SingletonHelper
	{
		private static final CommonHelperMethods INSTANCE = new CommonHelperMethods();
	}

	public static CommonHelperMethods getInstance()
	{
		return SingletonHelper.INSTANCE;
	}
	/***
	 *     +-+-+-+-+-+-+-+-+-+-+-+-+ +-+-+ +-+-+-+ +-+-+-+-+-+
	 *     |I|n|i|t|i|a|l|i|z|e|r|.| |D|o| |N|o|t| |A|l|t|e|r|
	 *     +-+-+-+-+-+-+-+-+-+-+-+-+ +-+-+ +-+-+-+ +-+-+-+-+-+
	 */

	/***
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 */

	public static List<String> returnStringAsList(String input)
	{
		List<String> output = new ArrayList<>();

		for(int i = 0; i < input.length(); i++)
		{
			output.add(String.valueOf(input.charAt(i)));
		}
		return output;
	}

	public static String reverseString(String s)
	{
		StringBuilder reversedString = new StringBuilder();
		// Reverse the string
		for (int i = s.length() - 1; i >= 0; i--)
		{
			char c = s.charAt(i);
			// Append char to StringBuilder
			reversedString.append(c);
		}
		return reversedString.toString();
	}

	public String signed32BitIntToBinaryString(int input)
	{
		String binaryBits = "";
		String signedBit = "0";
		if(input < 0)
		{
			signedBit = "1";
		}
		while(input > 0)
		{
			if(input % 2 == 1)
			{
				binaryBits += "1";
			}
			else
			{
				binaryBits += "0";
			}
			input = input/2;
		}
		while(binaryBits.length() < 31)
		{
			binaryBits += "0";
		}

		binaryBits = reverseString(binaryBits);

		String finalString = signedBit;
		if(signedBit.equals("1"))   //take the 2s compliment
		{
			for(int i = 0; i < binaryBits.length(); i++)
			{
				if(binaryBits.charAt(i) == '1')
				{
					finalString += "0";
				}
				else
				{
					finalString += "1";
				}
			}
		}
		else
		{
			finalString += binaryBits;
		}

		return finalString;
	}

	public Integer findDepthOfFlatBinaryTree(List<Integer> flatTree)
	{
		Integer length = flatTree.size();

		//Gets the log base 2 of the length.
		Integer result = (int)Math.ceil(Math.log(length+0.1) / Math.log(2));
		return result;
	}

	public static String integerToBinary(Integer input)
	{
		Integer num = input;
		String str = "";
		for(int a = 0; a < 8; a++)
		{
			if(num % 2 == 1)
			{
				str = "1" + str;
			}
			if(num % 2 == 0)
			{
				str = "0" + str;
			}
			num = num / 2;
		}
		return str;
	}

//	public static <T extends Comparable<T>> ArrayList<T> insertionSort(ArrayList<T> list, T value){
//		ArrayList<T> returnList = new ArrayList<>();
//		if(list.isEmpty()){
//			returnList.add(value);
//			return returnList;
//		}
//
//		int lowSearchIndex = 0;
//		int longSearchIndex = list.size()-1;
//		return returnList;
//	}

	public static Map<String, Integer> sortMapByValues(Map<String, Integer> inputMap)
	{
		Stream<Map.Entry<String,Integer>> sorted =
				inputMap.entrySet().stream()
						.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

		Map<String,Integer> charactersSortedByOccurrences = sorted.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return charactersSortedByOccurrences;
	}

	public static Integer summationOfSquares(List<Integer> numbers)
	{
		Integer sum = 0;
		for(Integer integer : numbers)
		{
			sum += integer * integer;
		}

		return sum;
	}

	public static List<Integer> separateDigitsIntoList(int n)
	{
		List<Integer> returnList = new ArrayList<>();

		while(n != 0)
		{
			int temp = n%10;
			Integer digit = temp;
			returnList.add(0,digit);
			n = n/10;
		}

		return returnList;
	}

	public static Pair<Integer,Integer> addTwoSingleDigitIntegers(Integer a, Integer b)
	{
		return addTwoSingleDigitIntegers(a,b,10);
	}

	public static Pair<Integer,Integer> addTwoSingleDigitIntegers(Integer a, Integer b, Integer base)
	{
		Integer sum = a+b;

		Pair<Integer, Integer> onesDigitAndOverflowPair = new Pair<>((sum % base), (sum / base));
		return onesDigitAndOverflowPair;
	}
}
