package test;

import org.junit.jupiter.api.Test;
import examSources.Find2dWords;
import examSources.NumUniqueEmailsValidator;

import java.util.ArrayList;
import java.util.List;

class NumUniqueEmailsValidatorTest
{
	@Test
	void happyCaseEasy()
	{
		String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		assert methodWrapper(emails).equals(2);
	}


	private Integer methodWrapper(String[] emails)
	{
		List<String> emailList = new ArrayList<>();
		for(int i = 0; i < emails.length; i++)
		{
			emailList.add(emails[i]);
		}

		return NumUniqueEmailsValidator.calculateUniqueEmails(emailList);
	}
}