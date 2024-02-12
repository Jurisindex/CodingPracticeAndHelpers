package test;

import org.junit.jupiter.api.Test;
import examSources.FriendCircleValidator;

class FriendCircleValidatorTest
{
	@Test
	void basicHappyCase()
	{
		int[][] friendMap = 
		{
				{1,1,0},
				{1,1,0},
				{0,0,1}
		};
		Integer expectedOut = 2;
		Integer actualOut = FriendCircleValidator.findCountOfFriendsGroup(friendMap);
		assert expectedOut.equals(actualOut);
	}
	
	@Test
	void caseFourCircles()
	{
		int[][] friendMap =
		{
			{1,0,0,1},
			{0,1,1,0},
			{0,1,1,1},
			{1,0,1,1}
		};
		Integer expectedOut = 1;
		Integer actualOut = FriendCircleValidator.findCountOfFriendsGroup(friendMap);
		assert expectedOut.equals(actualOut);
	}
}