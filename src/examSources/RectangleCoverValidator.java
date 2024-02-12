package examSources;

/*
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1: https://assets.leetcode.com/uploads/2018/10/22/rectangle_perfect.gif

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2: https://assets.leetcode.com/uploads/2018/10/22/rectangle_separated.gif

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.

Example 3: https://assets.leetcode.com/uploads/2018/10/22/rectangle_hole.gif

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4: https://assets.leetcode.com/uploads/2018/10/22/rectangle_intersect.gif

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.
 */


import org.w3c.dom.css.Rect;
import helperClasses.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class RectangleCoverValidator
{
	public static boolean isRectangleCover(int[][] rectangles)
	{
		//Find the bottomest point, the leftest point, the rightest point, and the topest point
		//N(log(n)) comparison of every rectangle to see if they intersect. If they do, return false
		//Then sum the area of the rects. If they equal the rect that would form going from bottom left to top right, it's a match.
		Rectangle greaterRectangle;
		Integer bottomestPoint = Integer.MAX_VALUE;
		Integer leftestPoint = Integer.MAX_VALUE;
		Integer rightestPoint = 0;
		Integer toppestPoint = 0;
		Integer areaSum = 0;
		List<Rectangle> rectangleList = new ArrayList<>();

		populateRectangles(rectangleList, rectangles);
		greaterRectangle = populatePoints(bottomestPoint, leftestPoint, rightestPoint, toppestPoint, rectangleList);

		areaSum = getSumOfAreas(rectangleList);

		if(areaSum.equals(greaterRectangle.area) == false)
		{
			return false;
		}
		else
		{
			return assertNoOverlaps(rectangleList);
		}
	}

	private static boolean assertNoOverlaps(List<Rectangle> rectangleList)
	{
		for(int i = 0; i < rectangleList.size(); i++)
		{
			Rectangle rect1 = rectangleList.get(i);
			for(int j = (i+1); j < rectangleList.size(); j++)
			{
				Rectangle rect2 = rectangleList.get(j);
				Rectangle lefterRect;
				Rectangle righterRect;
				Rectangle bottomerRect;
				Rectangle topperRect;

				if(rect2.bottomY < rect1.bottomY)
				{
					bottomerRect = rect2;
					topperRect = rect1;
				}
				else
				{
					bottomerRect = rect1;
					topperRect = rect2;
				}

				if(rect2.leftX < rect1.leftX)
				{
					lefterRect = rect2;
					righterRect = rect1;
				}
				else
				{
					lefterRect = rect1;
					righterRect = rect2;
				}
				//if leftmost of 2 is less than rightmost of 1 and bottommost of 2 is less than top most of 1
				if(lefterRect.rightX > righterRect.leftX && bottomerRect.topY > topperRect.bottomY)
				{
					return false;
				}
			}
		}
		return true;
	}

	private static Integer getSumOfAreas(List<Rectangle> rectangleList)
	{
		Integer sum = 0;
		for(Rectangle rect: rectangleList)
		{
			sum += rect.area;

		}
		return sum;
	}

	private static Rectangle populatePoints(Integer bottomestPoint, Integer leftestPoint, Integer rightestPoint, Integer toppestPoint, List<Rectangle> rectangleList)
	{
		for(Rectangle rect : rectangleList)
		{
			if(rect.leftX < leftestPoint) leftestPoint = rect.leftX;
			if(rect.rightX > rightestPoint) rightestPoint = rect.rightX;
			if(rect.bottomY < bottomestPoint) bottomestPoint = rect.bottomY;
			if(rect.topY > toppestPoint) toppestPoint = rect.topY;
		}
		return new Rectangle(leftestPoint, bottomestPoint, rightestPoint, toppestPoint);
	}

	private static void populateRectangles(List<Rectangle> rectangleList, int[][] rectangles)
	{
		for(int i = 0; i < rectangles.length; i++)
		{
			int[] rect = rectangles[i];
			rectangleList.add(new Rectangle(rect[0], rect[1], rect[2], rect[3]));
		}
	}
}
