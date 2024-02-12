package test;

import org.junit.jupiter.api.Test;
import examSources.LRUCache;

import java.util.ArrayList;

class LRUCacheTest
{
	LRUCache cache = null;

	@Test
	void simpleGetAndPut()
	{
		cache = new LRUCache(2);

		cache.put(1, 1);
		assert cache.get(1) == 1;
	}

	@Test
	void runtimeErrorLeetcodeTest()
	{
		/*
		["LRUCache","put","put","put","put","get","get"]
		[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
		 */
		cache = new LRUCache( 2);
		cache.put(2, 1);
		cache.put(1, 1);
		cache.put(2, 3);
		cache.put(4, 1);
		assert cache.get(1) == -1;
		assert cache.get(2) == 3;
	}

	@Test
	void secondRuntimeErrorLeetcodeTest()
	{
		String[] commands = new String[] {"LRUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
		Integer[][] input = new Integer[][] {{10},{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};
		performActions(commands, input);
	}
	
	@Test
	void wrongAnswerLeetcodeTest()
	{
		String[] commands = new String[] {"LRUCache","put","put","get","put","put","get"};
		Integer [][] input = new Integer[][] {{2},{2,1},{2,2},{2},{1,1},{4,1},{2}};
		performActions(commands, input);
	}
	
	@Test
	void wrongAnswerLeetcodeTest2()
	{
		String[] commands = new String[]{"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"};
		Integer[][] input = new Integer[][]{{2}, {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}};
		Object[] output = performActions(commands, input);
		Integer[] expected = {null,null,1,null,-1,null,-1,3,4};
		assertEquals(output, expected);
	}

	private void assertEquals(Object[] output, Integer[] expected)
	{
		for(int i = 0; i < expected.length; i++)
		{
			Integer integer = (Integer) output[i];
			if(integer == null)
			{
				assert expected[i] == null;
			}
			else
			{
				assert integer.equals(expected[i]);
			}
		}
	}

	private Object[] performActions(String[] commands, Integer[][] input)
	{
		assert commands.length == input.length;
		LRUCache cache = new LRUCache(input[0][0]);
		ArrayList<Integer> output = new ArrayList<>();

		System.out.println(input.length + " is the total.");
		for(int i = 1; i < input.length; i++)
		{
			System.out.println("Parsing operation " + i);
			if(commands[i].equals("put"))
			{
				System.out.println("Put " + input[i][0] + "," + input[i][1]);
				cache.put(input[i][0],input[i][1]);
				output.add(null);
				printStatusOfCache(cache);
			}
			else	//else it's a get
			{
				System.out.println("Get " + input[i][0]);
				Integer value = cache.get(input[i][0]);
				output.add(value);
				System.out.println("Get result: " + value);
			}
		}
		return output.toArray();
	}

	private void printStatusOfCache(LRUCache cache)
	{
		System.out.println("List of Cache: " + cache.keysCache.toString());
		System.out.println("List of keyValues: " + cache.keyValueMappings.toString());
	}
}