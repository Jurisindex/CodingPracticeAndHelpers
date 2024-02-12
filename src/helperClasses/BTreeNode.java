package helperClasses;

public class BTreeNode
{
	Integer value;
	BTreeNode leftChild;
	BTreeNode rightChild;

	BTreeNode()
	{
		value = null;
		leftChild = null;
		rightChild = null;
	}

	BTreeNode(Integer inputValue)
	{
		value = inputValue;
		leftChild = null;
		rightChild = null;
	}
}
