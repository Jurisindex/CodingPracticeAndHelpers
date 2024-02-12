package test;

import org.junit.jupiter.api.Test;
import examSources.LuckyNumberValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LuckyNumberValidatorTest
{
	@Test
	void isLuckyStreamOfNumbers()
	{
		List<Boolean> areLucky = LuckyNumberValidator.validateStreamOfIntegersAsLucky(12,14,15,19,23,24,42,47,69,99,100);
		assert areLucky.equals(Arrays.asList(false,false,false,true,true,false,false,false,false,false,true));
	}
}