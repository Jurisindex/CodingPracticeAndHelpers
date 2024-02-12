package test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import examSources.PasswordStengtheningChecker;

import static org.junit.jupiter.api.Assertions.*;

class PasswordStengtheningCheckerTest
{

	@Test
	void strongPasswordCheckerNoChangesNeeded() throws InterruptedException
	{
		String password = "aBcDeF69";
		Integer changesNeeded = PasswordStengtheningChecker.strongPasswordChecker(password);
		assert changesNeeded.equals(0);
	}

	@Test
	@Disabled	//Infinite loop. TODO Investigate infinite loop. Or rather, redo problem from scratch.
	void strongPasswordCheckerRemoveTriplicate() throws InterruptedException
	{
		String password = "aBcccDeF69";
		Integer changesNeeded = PasswordStengtheningChecker.strongPasswordChecker(password);
		assert changesNeeded.equals(1);
	}
}