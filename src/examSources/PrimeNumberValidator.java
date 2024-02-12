package examSources;

//Very simple. Is input number prime.
//Again, very simple.

public class PrimeNumberValidator
{
	public static boolean isPrime(int x)
	{
		int counter = 2;
		while( counter <= x/2 )
		{
			int moduloRemainder = x%counter;
			if (moduloRemainder == 0)
			{
				return false;
			}
			counter++;
		}
		return true;
	}
}
