package examSources;

/*
Given a movie's run time and given screentime available hours, I want to be able to play it:

-The maximum amount of times
-Give priority to intervals of 5 (playing it 215 minutes into operation over 214 minutes in, per say)

Example:
Movie Length : 107 minutes
Length of Operation: 862 minutes

I can play the movie 8 times, with it start on a 5 interval 5 times. On the 1st, 3rd, 4th, 6th, and 7th play.
 */

import helperClasses.Pair;

import java.util.ArrayList;
import java.util.List;

public class MovieTheatreTimingsValidator
{
	public static Pair<Integer, Integer> getMoviePlayTimeAnd5IntervalRunnings(Integer movieLength, Integer operatingTime)
	{
		Integer freeTime = operatingTime%movieLength;
		Integer playTimes = operatingTime/movieLength;
		Integer timesPlayedOnANickel = 0;
		Integer distancesEvaluated = 1;
		Integer currentTime_DiscardMeAfterLoop = 0;

		List<Integer> playStartTimes = new ArrayList<>(playTimes);
		while(playStartTimes.size() < playTimes)
		{
			//temp variable
			Integer onesComplimentOfStartTimeModFive = currentTime_DiscardMeAfterLoop;
			//only care about the mod5 remainder
			onesComplimentOfStartTimeModFive = onesComplimentOfStartTimeModFive % 5;
			//get the inverse of the mod5 remainder (4 would become 1)
			onesComplimentOfStartTimeModFive = Math.abs(onesComplimentOfStartTimeModFive-5);
			playStartTimes.add(onesComplimentOfStartTimeModFive);
			currentTime_DiscardMeAfterLoop += movieLength;
		}
		//What we actually have is a set of mods of 5 (for starting times)
		//{0, 2, 4, 1, 3, 0, 2, 4}
		//The problem is reduce-able to: Find the longest ascending non-linked sequence in an unsorted list.
		//
		//ie: {0,3,1,4,2,5,3,6}. Longest sequence: 0,1,4,5,6
		//Where this sequence is the distance from the next mod%5. Multiple values/lists can exist, so
		//an additional consideration is considering 0 and 5 for the same index if our free space is greater than or equal to 5.
		//given free space of say, 6... Anything that's 1 or less away (that is to say, 4s and 0s) can be expressed as
		//their default value away (4's count away from mod%5 == 0 is 1), or value + 5 (6, in this case)

		for(int i = 0; i < playStartTimes.size(); i++)
		{

		}


		Pair<Integer,Integer> playTimesAndOnFive = new Pair<>(playTimes, timesPlayedOnANickel);
		return playTimesAndOnFive;
	}
}
