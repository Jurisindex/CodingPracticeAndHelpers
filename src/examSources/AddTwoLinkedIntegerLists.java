package examSources;
/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */

import helperClasses.CommonHelperMethods;
import helperClasses.ListNode;
import helperClasses.Pair;

public class AddTwoLinkedIntegerLists
{

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		ListNode beginning = new ListNode(0);
		//Get the first digit in. Guaranteed to be at least 1 digit long even if 0.
		Pair<Integer,Integer> onesDigitAndOverflow = CommonHelperMethods.addTwoSingleDigitIntegers(l1.val,l2.val);

		Integer onesValue = onesDigitAndOverflow.getKey();
		beginning.val = onesValue;
		ListNode current = beginning;
		Integer overflowValue = onesDigitAndOverflow.getVal();

		l1 = l1.next;
		l2 = l2.next;
		//while both ListNodes not null, AND overflow value not 0, do stuffs. And overflow for handling 90 + 10.
		while(l1 != null || l2 != null || overflowValue > 0)
		{
			int l1Value = 0;
			int l2Value = 0;
			//set values if there are. Otherwise, assume as 0, and dont go down the list further
			if(l1 != null)
			{
				l1Value = l1.val;
				l1 = l1.next;
			}
			else l1Value = 0;
			if(l2 != null)
			{
				l2Value = l2.val;
				l2 = l2.next;
			}
			else l2Value = 0;

			//add the two values
			onesDigitAndOverflow = CommonHelperMethods.addTwoSingleDigitIntegers(l1Value, l2Value);
			onesValue = onesDigitAndOverflow.getKey();
			int tempOverflow = onesDigitAndOverflow.getVal();
			//Add the resulting two values + previous overflow. (A 99 + 01 situation gives 9+1 the second time around)
			onesDigitAndOverflow = CommonHelperMethods.addTwoSingleDigitIntegers(onesValue, overflowValue);
			//Mark down the ones and carry on the overflow.
			current.next = new ListNode(onesDigitAndOverflow.getKey());
			current = current.next;
			//a 99 + 01 situation should yield a 1 0 0, so the 2nd time it has to overflow
			overflowValue = tempOverflow + onesDigitAndOverflow.getVal();
		}
		return beginning;
	}
}
