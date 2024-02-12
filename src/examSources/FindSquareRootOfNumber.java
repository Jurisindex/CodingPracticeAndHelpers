package examSources;

/*
	sqrt(2)
	guess 1.5
	eval 1.5^2 = too high, go to 1.4, too low, 1.6
 */

public class FindSquareRootOfNumber
{
	public static Double findSquareRootOfNumber(Double square)
	{
		//Floor of 0, ceiling of square
		Double floor = 0.0;
		Double ceiling = 0.0 + square;

		//Trial number to square = (ceiling-floor)/2
		Double trialSquare = ((ceiling-floor)/2) + floor;
		Double squaredResult = trialSquare * trialSquare;

		while(!resultNotWithinDesiredRange(square, squaredResult))
		{
			//If too high, new ceiling is the previous trial number
			if(squaredResult > square)
			{
				ceiling = trialSquare;
			}
			//If too low, new floor is the previous trial number
			if(squaredResult < square)
			{
				floor = trialSquare;
			}

			trialSquare = ((ceiling-floor)/2) + floor;
			squaredResult = trialSquare * trialSquare;
			//Iterate till match (or at least within some very small margin of error)
		}


		return trialSquare;
	}

	private static boolean resultNotWithinDesiredRange(Double square, Double squaredResult)
	{
		//Evaluate that they're close _enough_ to be considered the same
		Double difference = squaredResult - square;
		if(Math.abs(difference) < 0.0000000000001)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
