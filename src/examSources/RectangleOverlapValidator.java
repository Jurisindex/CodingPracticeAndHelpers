package examSources;

/*
	A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.

	Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.

	Given two (axis-aligned) rectangles, return whether they overlap.

	Example 1:

	Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
	Output: true
	Example 2:

	Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
	Output: false
	Notes:

	Both rectangles rec1 and rec2 are lists of 4 integers.
	All coordinates in rectangles will be between -10^9 and 10^9.
*/

public class RectangleOverlapValidator
{
	public static boolean isRectangleOverlap(int[] rec1, int[] rec2)
	{
		//rectangle format
		//x1,y1,x2,y2
		//bottom-left,top-right

		//Find out which one is higher up, or equal
		boolean possibleYOverlap = false;
		boolean possibleXOverlap = false;
		if(rec1[3] > rec2[3])
		{
			//rec 1 is higher
			System.out.println("rec1 higher");
			possibleYOverlap = isTallerOnePossiblyOverlapping(rec1, rec2);
		}
		else
		{
			//rec 2 is higher
			System.out.println("rec2 higher");
			possibleYOverlap = isTallerOnePossiblyOverlapping(rec2, rec1);
		}
		//Find out which one is further left, or equal
		if(possibleYOverlap && (rec1[0] < rec2[0]))    //no need to eval if no previous overlap
		{
			//rec1 is lefter
			System.out.println("rec1 lefter");
			possibleXOverlap = isLefterOnePossiblyOverlapping(rec1, rec2);
		}
		else
		{
			//rec2 is lefter
			System.out.println("rec2 lefter");
			possibleXOverlap = isLefterOnePossiblyOverlapping(rec2, rec1);
		}

		if(possibleYOverlap && possibleXOverlap)
		{
			return true;
		}
		else return false;
	}

	private static boolean isTallerOnePossiblyOverlapping(int[] higherRec, int[] lowerRec)
	{
		//is tallest point of lower, taller, than lowest point of taller
		System.out.println(lowerRec[3] + "," + higherRec[1]);
		return lowerRec[3] > higherRec[1];
	}

	private static boolean isLefterOnePossiblyOverlapping(int[] lefterRec, int[] righterRec)
	{
		//is leftest point of righter, lefter, than rightest point of lefter
		System.out.println(righterRec[0] + "," + lefterRec[2]);
		return righterRec[0] < lefterRec[2];
	}

}