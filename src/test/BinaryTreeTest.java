package test;

import org.junit.jupiter.api.Test;
import helperClasses.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest
{
	@Test
	public void testAddingAndPrintingList()
	{
		List<Integer> inputs = Arrays.asList(999,41598,46,56,32,6,5413,2169879,5,64,987,6321,324,9687,95,632,16,579,546,21,3216,5456,521,30,65,4965,216,2196,545,6532);
		BinaryTree tree = new BinaryTree();
		tree.add(inputs);
		tree.displayTree(BinaryTree.TRAVERSAL_MODE.inOrder);
		tree.displayTree(BinaryTree.TRAVERSAL_MODE.preOrder);
		tree.displayTree(BinaryTree.TRAVERSAL_MODE.postOrder);
	}
}