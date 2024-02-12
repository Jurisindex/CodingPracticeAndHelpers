package helperClasses;

public class Rectangle
{
	public int leftX;
	public int rightX;
	public int topY;
	public int bottomY;
	public int area;

	public Rectangle (int x1, int y1, int x2, int y2)
	{
		leftX = x1;
		rightX = x2;
		bottomY = y1;
		topY = y2;

		//assuming positive integers only.
		area = ((x2 - x1) * (y2 - y1));
	}
}
