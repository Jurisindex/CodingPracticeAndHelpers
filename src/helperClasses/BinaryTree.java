package helperClasses;

import java.util.List;

public class BinaryTree
{
	public enum TRAVERSAL_MODE
	{
		preOrder,
		inOrder,
		postOrder
	}

	public BTreeNode root;

	public BinaryTree()
	{
		root = null;
	}

	BinaryTree(Integer input)
	{
		root = new BTreeNode(input);
	}


	private void add(BTreeNode input, BTreeNode currentNode)
	{
		if(root == null)
		{
			root = input;
			return;
		}
		//Select the left or right child, depending on value comparison.
		if(input.value <= currentNode.value)
		{
			//We are at a leaf with no child, and are adding a new node to the tree.
			if(currentNode.leftChild == null)
			{
				System.out.println("Adding a " + input.value + " as the LEFT child of " + currentNode.value);
				currentNode.leftChild = input;
			}
			//Haven't found the right place yet, recurse.
			else
			{
				add(input, currentNode.leftChild);
			}
		}
		else
		{
			//We are at a leaf with no child, and are adding a new node to the tree.
			if(currentNode.rightChild == null)
			{
				System.out.println("Adding a " + input.value + " as the RIGHT child of " + currentNode.value);
				currentNode.rightChild = input;
			}
			//Haven't found the right place yet, recurse.
			else
			{
				add(input, currentNode.rightChild);
			}
		}

//		//Select the left or right child, depending on value comparison.
//		BTreeNode nextNode = input.value <= currentNode.value ? currentNode.leftChild : currentNode.rightChild;
//		if(nextNode == null)
//		{
//			//We are at a leaf with no child, and are adding a new node to the tree.
//
//			nextNode = input;	//TODO: Find out how to assign the value and not the pointer, or parameterize the left/right child.
//		}
//		else
//		{
//			//Haven't found the right place yet, recurse.
//			add(input, nextNode);
//		}
	}

	public void displayTree()
	{
		displayTree(TRAVERSAL_MODE.inOrder);
	}

	public void displayTree(TRAVERSAL_MODE inOrder)
	{
		System.out.println("Displaying tree in " + inOrder + " order.");
		displayTreeHelper(inOrder, root);
		System.out.println();
		System.out.println("Done displaying tree.");
	}

	public void displayTreeHelper(TRAVERSAL_MODE mode, BTreeNode currentNode)
	{
		if(currentNode == null)
		{
			return;
		}
		if(mode.equals(TRAVERSAL_MODE.preOrder))	System.out.print(currentNode.value + " | ");
		displayTreeHelper(mode, currentNode.leftChild);
		if(mode.equals(TRAVERSAL_MODE.inOrder))		System.out.print(currentNode.value + " | ");
		displayTreeHelper(mode, currentNode.rightChild);
		if(mode.equals(TRAVERSAL_MODE.postOrder))	System.out.print(currentNode.value + " | ");
	}

	public void add(Integer input)
	{
		add(new BTreeNode(input), root);
	}

	public void add(List<Integer> inputs)
	{
		for(Integer i : inputs)
		{
			add(i);
		}
	}
}
