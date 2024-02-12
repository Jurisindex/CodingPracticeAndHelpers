package examSources;

public class LongestValidParenthesisValidator
{
	public static String getLongestValidParenthesisSubstring(String inputParensString)
	{
		if(inputParensString == null)
		{
			inputParensString = "";
		}
		if(ParenthesisValidator.isValidParensGrouping(inputParensString) || inputParensString.equals(""))
		{
			return inputParensString;
		}

		//We can split this into different valid sections we can check easily by validating no right parens bit, because that's not fixable.
		//Example:
		//"(((((((((((..." can be valid, and so we have to consider it in our search, and look to the right including that.
		//"()()()()())...." cannot be valid, so we can segment the parts before and after this as valid subsections to look at.

		for(int i = inputParensString.length()-1; i > 1; i--)
		{
			System.out.println("Looking a chunks of size " + i);
			for(int j = 0; j <= inputParensString.length()-i; j++)
			{
				System.out.println("i: " + i + ", j: " + j);
				String subsectionConsidered = inputParensString.substring(j, i+j);
				System.out.println("Considering subsection " + subsectionConsidered);
				if(ParenthesisValidator.isValidParensGrouping(subsectionConsidered))
				{
					System.out.println(subsectionConsidered + " is a valid Parenthesis Grouping. Returning.");
					return subsectionConsidered;
				}
				System.out.println(subsectionConsidered + " is not a valid Parenthesis subsection.");
			}
		}
		return "";
	}
}
