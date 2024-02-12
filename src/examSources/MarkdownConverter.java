package examSources;

public class MarkdownConverter
{
	public static String parseMarkdown(String markdown)
	{
		String returnValue = "";

		char c = markdown.charAt(0);
		//Process char
		if(c == '#')
		{
			returnValue = processMarkdownBeginnings(markdown);
		}

		if(returnValue.equals(""))
		{
			return markdown;
		}
		else
		{
			return returnValue;
		}
	}

	private static String processMarkdownBeginnings(String markdown)
	{
		int hashtagCounter = 0;
		for (int index = 0; index < markdown.length(); index++)
		{
			char c = markdown.charAt(index);
			if(c == '#')
			{
				hashtagCounter++;
				if(hashtagCounter > 6)
				{
					return "";
				}
			}
			else if(markdown.length() > index+1 && markdown.charAt(index) == ' ')
			{
				String hashtagCounterString = String.valueOf(hashtagCounter);
				String substringedString = markdown.substring(index+1);
				substringedString = substringedString.trim();
				String returnValue = "<h" + hashtagCounterString + ">" + substringedString + "</h" + hashtagCounterString + ">";
				return returnValue;
			}
			else
			{
				return "";
			}
		}
		return "";
	}
}
