package examSources;

/*
There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCircleValidator
{

	public static Integer findCountOfFriendsGroup(int[][] friendMap)
	{
		Integer friendCircles = 0;
		List<List<Integer>> friendsFriends = new ArrayList<>();
		for(int i = 0; i < friendMap.length; i++)
		{
			List<Integer> friends = new ArrayList<>();
			friendsFriends.add(friends);
		}
		//Now we have N empty lists. Cool.

		for(int i = 0; i < friendMap.length; i++)
		{
			List<Integer> friendsOfCurrentPerson = friendsFriends.get(i);

			//guaranteed to be N*N
			for(int j = 0; j < friendMap.length; j++)
			{
				if(friendMap[i][j] == 1 && !friendsOfCurrentPerson.contains(j))
				{
					friendsOfCurrentPerson.add(j);
				}
			}
			friendsFriends.set(i, friendsOfCurrentPerson);
		}

		HashMap<Integer,Boolean> friendsMapped = getEmptyFriendsMap(friendMap);
		//Now we have N false friends. Cool.

		while(friendsMapped.values().contains(false))
		{
			Integer currentFriendStartIndex = 0;
			for(Map.Entry friendEntry : friendsMapped.entrySet())
			{
				if(friendEntry.getValue().equals(false))
				{
					currentFriendStartIndex = (Integer) friendEntry.getKey();
					break;
				}
			}
			//do the thing
			markMappedChainsAsTrue(currentFriendStartIndex, friendsFriends, friendsMapped);
			friendCircles++;
		}

		return friendCircles;
	}

	private static void markMappedChainsAsTrue(Integer currentFriendStartIndex, List<List<Integer>> friendsFriends,
											   HashMap<Integer, Boolean> friendsMapped)
	{
		List<Integer> friendsOfCurrent = new ArrayList<>();
		//iterate through friends of current
		for(Integer friendOfCurrent : friendsFriends.get(currentFriendStartIndex))
		{
			if(friendsMapped.get(friendOfCurrent))
			{
				//skip this
				continue;
			}
			else
			{
				//Any found, mark true in friendsMapped.
				//Add them to a list to eval later
				friendsOfCurrent.add(friendOfCurrent);
				friendsMapped.put(friendOfCurrent, true);
			}
		}
		//If at least 1 item added, iterate. ELSE, don't iterate.
		if(friendsOfCurrent.isEmpty())
		{
			return;
		}
		else
		{
			for(Integer friend : friendsOfCurrent)
			{
				markMappedChainsAsTrue(friend, friendsFriends, friendsMapped);
			}
		}
	}

	private static HashMap<Integer, Boolean> getEmptyFriendsMap(int[][] friendMap)
	{
		HashMap<Integer,Boolean> friendsAccountedFor = new HashMap<>();
		for(int i = 0; i < friendMap.length; i++)
		{
			friendsAccountedFor.put(i, false);
		}
		return friendsAccountedFor;
	}
}
