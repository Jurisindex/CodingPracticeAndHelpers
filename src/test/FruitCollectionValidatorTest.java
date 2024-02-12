package test;

import org.junit.jupiter.api.Test;
import examSources.Find2dWords;
import examSources.FruitCollectionValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FruitCollectionValidatorTest
{
	@Test
	void basicFruitCollection()
	{
		Integer[] trees = {3,3,3,1,2,1,1,2,3,3,4};
		//assert methodWrapper(trees).equals(5);
		System.out.println(methodWrapper(trees));
	}

	@Test
	void introducingCombos()
	{
		Integer[] trees = {0,1,6,6,4,4,6};
		System.out.println(methodWrapper(trees));
	}

	@Test
	void comboFailure()
	{
		Integer[] trees = {6,2,1,1,3,6,6};
		System.out.println(methodWrapper(trees));
	}

	private Integer methodWrapper(Integer[] fruitTrees)
	{
		List<Integer> treeList = new ArrayList<>();
		for(int i = 0; i < fruitTrees.length; i++)
		{
			treeList.add(fruitTrees[i]);
		}

		return FruitCollectionValidator.maxFruitsCollectable(treeList);
	}
}