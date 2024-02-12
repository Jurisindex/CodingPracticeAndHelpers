package examSources;

import java.util.List;
import java.util.Map;

public class ParseGlobalByteDataBreakdown
{
	public static double getTrafficAsPercentageOfTotal(Map<String,Long> byteBreakdown, List<String> countriesWanted)
	{
		//Get the totals of bytes.
		Long byteTotal = 0L;
		Long countriesWantedTotal = 0L;
		for(Map.Entry entry : byteBreakdown.entrySet())
		{
			Long bytes = (Long) entry.getValue();
			byteTotal += bytes;

			if(countriesWanted.contains(entry.getKey()))
			{
				countriesWantedTotal += bytes;
			}
		}

		Double percentOfTotal = ((double) countriesWantedTotal / byteTotal) * 100;

		return percentOfTotal;
	}
}
