package test;

import org.junit.jupiter.api.Test;
import examSources.MarkdownConverter;

class MarkdownConverterTest
{
	@Test
	void parseMarkdownPositiveCase()
	{
		String parsedMarkdown = MarkdownConverter.parseMarkdown("### ### Header");
		assert parsedMarkdown.equals("<h3>### Header</h3>");
	}

	@Test
	void parseMarkdownPositiveWhitespaceCase()
	{
		String parsedMarkdown = MarkdownConverter.parseMarkdown("###           ### Header     ");
		assert parsedMarkdown.equals("<h3>### Header</h3>");
	}

	@Test
	void parseMarkdownNegativeCase()
	{
		String parsedMarkdown = MarkdownConverter.parseMarkdown("Bababooie ### ### Header");
		assert parsedMarkdown.equals("Bababooie ### ### Header");
	}
}