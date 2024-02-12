package test;

import org.junit.jupiter.api.Test;
import helperClasses.ListNode;
import examSources.AddTwoLinkedIntegerLists;

class AddTwoLinkedIntegerListsTest
{
	@Test
	void leetcodeExamplePositiveCase()
	{
		//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
		//Output: 7 -> 0 -> 8
		//Explanation: 342 + 465 = 807

		ListNode listNode1 = new ListNode(2);
		listNode1.next = new ListNode(4);
		listNode1.next.next = new ListNode(3);
		ListNode listNode2 = new ListNode(5);
		listNode2.next = new ListNode(6);
		listNode2.next.next = new ListNode(4);

		ListNode resultNode = AddTwoLinkedIntegerLists.addTwoNumbers(listNode1, listNode2);
		assert resultNode.val == 7;
		assert resultNode.next.val == 0;
		assert resultNode.next.next.val == 8;
	}

	@Test
	void leetcodeExamplePositiveEdgeCase99And1()
	{
		//Input: (9 -> 9) + (1)
		//Output: 0 -> 0 -> 1
		//Explanation: 99 + 1 = 100

		ListNode listNode1 = new ListNode(9);
		listNode1.next = new ListNode(9);
		ListNode listNode2 = new ListNode(1);

		ListNode resultNode = AddTwoLinkedIntegerLists.addTwoNumbers(listNode1, listNode2);
		assert resultNode.val == 0;
		assert resultNode.next.val == 0;
		assert resultNode.next.next.val == 1;
	}
}