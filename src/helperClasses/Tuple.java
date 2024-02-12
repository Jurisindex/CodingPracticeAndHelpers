package helperClasses;

import java.io.Serializable;

/**
 * A convenience class to represent name-value pairs.
 */

public class Tuple<K,V,I> implements Serializable
{
	private K key;

	public K getKey() { return key; }

	private V value;

	public V getValue() { return value; }

	private I identifier;

	public I getIdentifier() { return identifier; }

	public Tuple(K key, V value, I identifier)
	{
		this.key = key;
		this.value = value;
		this.identifier = identifier;
	}

	@Override
	public String toString()
	{
		return key + "=" + value + "=" + identifier;
	}

	@Override
	public int hashCode()
	{
		// name's hashCode is multiplied by an arbitrary prime number (13)
		// in order to make sure there is a difference in the hashCode between
		// these two parameters:
		//  name: a  value: aa
		//  name: aa value: a
		return key.hashCode() * 13 + (value == null ? 0 : value.hashCode()) + (identifier == null ? 0 : identifier.hashCode());
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o instanceof Tuple)
		{
			Tuple tuple = (Tuple) o;
			if (key != null ? !key.equals(tuple.key) : tuple.key != null) return false;
			if (value != null ? !value.equals(tuple.value) : tuple.value != null) return false;
			if (identifier != null ? !identifier.equals(tuple.identifier) : tuple.identifier != null) return false;
			return true;
		}
		return false;
	}
}