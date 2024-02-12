package examSources;

import helperClasses.ListNode;

import java.util.LinkedList;

/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
 */

public class MergeTwoSortedListsValidator
{
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2)
	{
		LinkedList<Integer> finalList = new LinkedList<>();

		ListNode l1Temp = l1;
		ListNode l2Temp = l2;

		while(l1Temp != null || l2Temp != null)
		{
			if(l1Temp == null)
			{
				finalList.addLast(l2Temp.val);
				l2Temp = l2Temp.next;
			}
			else if(l2Temp == null)
			{
				finalList.addLast(l1Temp.val);
				l1Temp = l1Temp.next;
			}
			else
			{
				if(l1Temp.val <= l2Temp.val)
				{
					finalList.addLast(l1Temp.val);
					l1Temp = l1Temp.next;
				}
				else
				{
					finalList.addLast(l2Temp.val);
					l2Temp = l2Temp.next;
				}
			}
		}
		//TODO: Can refactor this out and do the returnValue sorting in-traversal in the while loop above for faster run time
		//Now we have a linked list with our in-order values. How do we make it into a ListNode representation?
		if(finalList.size() == 0) return null;

		ListNode returnValue = new ListNode(finalList.get(0));
		ListNode tmpValue = returnValue;
		for(int i = 1; i < finalList.size(); i++)
		{
			tmpValue.next = new ListNode(finalList.get(i));
			tmpValue = tmpValue.next;
		}
		return returnValue;
	}
}
