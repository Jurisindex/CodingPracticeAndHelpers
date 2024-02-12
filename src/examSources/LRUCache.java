package examSources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
Design and implement a data structure for Least Recently Used (LRU) cache.
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2  /* the capacity /* );

		cache.put(1, 1);
		cache.put(2, 2);
		cache.get(1);       // returns 1
		cache.put(3, 3);    // evicts key 2
		cache.get(2);       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		cache.get(1);       // returns -1 (not found)
		cache.get(3);       // returns 3
		cache.get(4);       // returns 4
 */
public class LRUCache
{
	public LinkedList<Integer> keysCache;
	//ArrayList<Integer> keysCache;	//Best way involves only using ArrayList<CustomNode> keysCache.
									//CustomNode class would have Integer key, implement the compare function like an Integer
									//And have an int/Integer value as an addon item. Key here to implement the .equals
									//function of it to use ONLY the key.
	public HashMap<Integer, Integer> keyValueMappings;
	//Better to have hash map of <Integer, ListNode> , where the listNode would be a doubly linked list pointer
	//And the integer would be its key. The node would contain the key and value. Position inferred from prev and next pointers.
	public Integer currentCapacity;
	public Integer maxCapacity;

	public LRUCache(int capacity)
	{
		//keysCache = new ArrayList<>(capacity);
		keysCache = new LinkedList<Integer>();
		maxCapacity = capacity;
		currentCapacity = 0;
		keyValueMappings = new HashMap<>(capacity);
	}

	//Returns value.
	//Expectation: Value returned is always positive.
	//If not found, returns negative 1.
	public int get(int key)
	{
		//find it
		Integer keyIndex = keysCache.indexOf(Integer.valueOf(key));
		//If not found, return -1;
		if(keyIndex == -1)
		{
			return -1;
		}
		//Otherwise return the value, and set it as most recently accessed.
		Integer keyMostRecentlyAccessed = keysCache.get(keyIndex);
		keysCache.remove(keyMostRecentlyAccessed);
		keysCache.addFirst(keyMostRecentlyAccessed);
		return keyValueMappings.get(keyMostRecentlyAccessed);
	}

	public void put(int key, int value)
	{
		//do we just need to update?
		if(get(key) > -1)
		{
			//set the value. The keysCache already put it in front
			keyValueMappings.put(key, value);
			return;
		}

		//Are we at capacity?
		if(currentCapacity >= maxCapacity)
		{
			//It is not found, so we must make room to add it
			//If so, calculate the least old //automagically done
			//delete it
			Integer keyRemoved = keysCache.removeLast();
			keyValueMappings.remove(keyRemoved);

			//add the new one
			keysCache.addFirst(key);
			keyValueMappings.put(key, value);
		}
		else
		{
			//Add the item normally otherwise.
			keysCache.addFirst(key);
			keyValueMappings.put(key, value);
			currentCapacity++;
		}
	}
}
