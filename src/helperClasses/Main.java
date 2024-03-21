package helperClasses;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

	public static void main (String [] args) throws Exception
	{
		//getHitProbability
	}
}

////Question Logger
//	Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
//
//		Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
//
//		It is possible that several messages arrive roughly at the same time.
//
//		Example:
//
//		Logger logger = new Logger();
//
//		// logging string "foo" at timestamp 1
//		logger.shouldPrintMessage(1, "foo"); returns true;
//
//		// logging string "bar" at timestamp 2
//		logger.shouldPrintMessage(2,"bar"); returns true;
//
//		// logging string "foo" at timestamp 3
//		logger.shouldPrintMessage(3,"foo"); returns false;
//
//		// logging string "bar" at timestamp 8
//		logger.shouldPrintMessage(8,"bar"); returns false;
//
//		// logging string "foo" at timestamp 10
//		logger.shouldPrintMessage(10,"foo"); returns false;
//
//		// logging string "foo" at timestamp 11
//		logger.shouldPrintMessage(11,"foo"); returns true;
//
//
//
//class Logger {
//	Map<String, Integer> messageTimestampMap;
//
//	/** Initialize your data structure here. */
//	public Logger()
//	{
//		messageTimestampMap = new HashMap<String, Integer>();
//	}
//
//	/** Returns true if the message should be printed in the given timestamp, otherwise returns false.
//	 If this method returns false, the message will not be printed.
//	 The timestamp is in seconds granularity. */
//	public boolean shouldPrintMessage(int timestamp, String message)
//	{
//		//check if it's in the list
//		Integer lastKnownTime = messageTimestampMap.get(message);
//		if(lastKnownTime == null)
//		{
//			//if it's not, print it, and record the timestamp
//			messageTimestampMap.put(message, timestamp);
//			return true;
//		}
//		else
//		{
//			//if it's in the list, retrieve its timestamp value, //done
//			//, check if it's been 10 seconds,
//			int timeDifference = timestamp - lastKnownTime;
//			if(timeDifference >= 10)
//			{
//				//, if it has, print and overwrite,
//				messageTimestampMap.put(message, timestamp);
//				return true;
//			}
//			else
//			{
//				//,otherwise return false
//				return false;
//			}
//		}
//	}
//}
//
///**
// * Your Logger object will be instantiated and called as such:
// * Logger obj = new Logger();
// * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
// */

////

////Question Assign Bikes
//On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
//
//		Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.
//
//		The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
//
//		Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
//
//
//
//		Example 1:
//
//
//
//		Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
//		Output: [1,0]
//		Explanation:
//		Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
//		Example 2:
//
//
//
//		Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
//		Output: [0,2,1]
//		Explanation:
//		Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
//
//
//		Note:
//
//		0 <= workers[i][j], bikes[i][j] < 1000
//		All worker and bike locations are distinct.
//		1 <= workers.length <= bikes.length <= 1000
//
//	public int[] assignBikes(int[][] workers, int[][] bikes)
//	{
//		int shortestManhattan = Integer.MAX_VALUE;
//		Tuple<Integer,Integer,Integer> shortestBike = null;
//		List<Tuple<Integer,Integer,Integer>> workerCoords = new ArrayList<>();
//		List<Tuple<Integer,Integer,Integer>> bikeCoords = new ArrayList<>();
//		for(int i = 0; i < workers.length; i++)
//		{
//			workerCoords.add(new Tuple<>(workers[i][0],workers[i][1], i));
//			bikeCoords.add(new Tuple<>(bikes[i][0],bikes[i][1], i));
//		}
//		int[] resultSet = new int[workers.length];
//
//		//for each worker
//		for(Tuple<Integer,Integer,Integer> werkwerk : workerCoords)
//		{
//			//find a bike's manhattan value
//			for(Tuple<Integer,Integer,Integer> bikeybike : bikeCoords)
//			{
//				int manhattanDistance = Manhattan(werkwerk.getKey(),werkwerk.getValue(),
//						bikeybike.getKey(),bikeybike.getValue());
//				if(manhattanDistance < shortestManhattan)
//				{
//					shortestManhattan = manhattanDistance;
//					shortestBike = bikeybike;
//				}
//			}
//			//assign that worker that bike, and remove that bike from further computations
//			resultSet[werkwerk.getIdentifier()] = shortestBike.getIdentifier();
//			bikeCoords.remove(shortestBike);
//			shortestManhattan = Integer.MAX_VALUE;
//			shortestBike = null;
//		}
//
//
//
//		return resultSet;
//	}