package test;

import org.junit.jupiter.api.Test;
import examSources.RectangleCoverValidator;

import static org.junit.jupiter.api.Assertions.*;

class RectangleCoverValidatorTest
{
	@Test
	public void testCase1()
	{
		int [][] rectangles = {  {1,1,3,3},  {3,1,4,2},  {3,2,4,4},  {1,3,2,4},  {2,3,3,4}};
		assert RectangleCoverValidator.isRectangleCover(rectangles) == true;
	}
	
	@Test
	public void failCase1()
	{
		int [][] rectangles = {{0,0,1,1},{0,1,3,2},{1,0,2,2}};
		assert RectangleCoverValidator.isRectangleCover(rectangles) == false;
	}
	
	@Test
	public void failCase2()
	{
		int [][] rectangles = {{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}};
		assert RectangleCoverValidator.isRectangleCover(rectangles) == true;
	}
}