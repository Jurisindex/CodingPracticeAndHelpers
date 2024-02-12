package test;

import org.junit.jupiter.api.Test;
import examSources.DoubleReplacementValidator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DoubleReplacementValidatorTest
{
	private static DoubleReplacementValidator.DoubleSubstitution ds = null;

	@Test
	public void doubleSubstitutionTest()
	{
		// Implement test cases here
		Map<String, String> key = new HashMap<>();
		key.put("a", "ba");
		key.put("b", "ab");
		key.put("t", "ab");
		key.put("m", "ca");
		key.put("n", "cc");
		assertEquals("abbaab", ds.encrypt(key, "bat"));
		assertEquals(new HashSet<String>(Arrays.asList(new String[] {"man"})), ds.decrypt(key, "cabacc"));
		assertEquals(new HashSet<String>(Arrays.asList(new String[] {"bat", "tab", "tat"})), ds.decrypt(key, "abbaab"));
	}
}