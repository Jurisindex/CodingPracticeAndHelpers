package examSources;

import helperClasses.CommonHelperMethods;

import java.util.List;

public class ParenthesisValidator
{
	public static boolean isValidParensGrouping(String input)
	{
		int openCount = 0;
		List<String> parens = CommonHelperMethods.returnStringAsList(input);

		for(String s : parens)
		{
			if(s.equals("("))
			{
				openCount++;
			}
			else
			if(s.equals(")"))
			{
				openCount--;
			}
			if (openCount < 0) return false;
		}
		if(openCount != 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
