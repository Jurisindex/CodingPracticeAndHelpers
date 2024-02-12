package test;

import org.junit.jupiter.api.Test;
import examSources.PermutationsValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PermutationsValidatorTest
{
	@Test
	public void testJavaContains()
	{
		List<List<Integer>> outputTest = new ArrayList<>();
		List<Integer> inputList1 = new ArrayList<>(Arrays.asList(1,2,3));
		outputTest.add(inputList1);

		List<Integer> inputList2 = new ArrayList<>(Arrays.asList(3,2,1));
		if(!outputTest.contains(inputList2))
		{
			outputTest.add(inputList2);
		}

		assert (!inputList1.equals(inputList2));
	}

	@Test
	public void happy123Case()
	{
		List<Integer> inputList = new ArrayList<>(Arrays.asList(1,2,3));

		List<List<Integer>> output = PermutationsValidator.getPermutationsIteratively(inputList);
		System.out.println("Forget automatic validation on this");
	}

	@Test
	public void happy1234Case()
	{
		//failing
		List<Integer> inputList = new ArrayList<>(Arrays.asList(1,2,3,4));

		List<List<Integer>> output = PermutationsValidator.getPermutationsIteratively(inputList);
		System.out.println("Forget automatic validation on this");
	}

	@Test
	public void happy12345Case()
	{
		//failing
		List<Integer> inputList = new ArrayList<>(Arrays.asList(1,2,3,4,5));

		List<List<Integer>> output = PermutationsValidator.getPermutationsIteratively(inputList);
		System.out.println("Forget automatic validation on this");
	}

	@Test
	public void happy123CaseRecruse()
	{
		List<Integer> inputList = new ArrayList<>(Arrays.asList(1,2,3));

		List<List<Integer>> output = PermutationsValidator.getPermutationsRecursively(inputList);
		System.out.println("Forget automatic validation on this");
	}

	@Test
	public void happy112CaseRecruse()
	{
		List<Integer> inputList = new ArrayList<>(Arrays.asList(1,1,2));

		List<List<Integer>> output = PermutationsValidator.getPermutationsRecursively(inputList);
		System.out.println("Forget automatic validation on this");
	}

	@Test
	public void happy1234CaseRecruse()
	{
		List<Integer> inputList = new ArrayList<>(Arrays.asList(1,2,3,4));

		List<List<Integer>> output = PermutationsValidator.getPermutationsRecursively(inputList);
		System.out.println("Forget automatic validation on this");
	}

	@Test
	public void happy12345CaseRecruse()
	{
		List<Integer> inputList = new ArrayList<>(Arrays.asList(1,2,3,4,5));

		List<List<Integer>> output = PermutationsValidator.getPermutationsRecursively(inputList);
		System.out.println("Forget automatic validation on this");
	}

	@Test
	public void happy123455CaseRecruse()
	{
		//passes
		List<Integer> inputList = new ArrayList<>(Arrays.asList(1,2,3,4,5,5));

		List<List<Integer>> output = PermutationsValidator.getPermutationsRecursively(inputList);
		System.out.println("Forget automatic validation on this");
	}
}