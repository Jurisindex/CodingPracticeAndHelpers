package test;

import org.junit.jupiter.api.Test;
import helperClasses.Pair;
import examSources.MovieTheatreTimingsValidator;

class MovieTheatreTimingsValidatorTest
{
	@Test
	void defaultTestCase()
	{
//		Example:
//		Movie Length : 107 minutes
//		Length of Operation: 862 minutes
		Pair<Integer, Integer> result = MovieTheatreTimingsValidator.getMoviePlayTimeAnd5IntervalRunnings(107, 862);
		assert result.getKey().equals(8);
		assert result.getVal().equals(5);
	}
}