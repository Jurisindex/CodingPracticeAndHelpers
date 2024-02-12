package test;

import org.junit.jupiter.api.Test;
import helperClasses.Pair;
import examSources.HealthCoverageChildCountValidator;
import helperClasses.Coveree;
import helperClasses.MedicalPlan;

import java.util.ArrayList;
import java.util.List;

class HealthCoverageChildCountValidatorTest
{

	@Test
	void childCountInMedicalPlanDefaultCase()
	{
		//Expected Output: (1,3), (2,2)
		MedicalPlan plan = new MedicalPlan();
		Coveree c1 = new Coveree(5, "FAM2", "CHILD", "MED"); plan.coverees.add(c1);
		Coveree c2 = new Coveree(2, "FAM1", "SPOUSE", "BEH"); plan.coverees.add(c2);
		Coveree c3 = new Coveree(3, "FAM1", "CHILD", "MED"); plan.coverees.add(c3);
		Coveree c4 = new Coveree(1, "FAM1", "PRIMARY", "MED"); plan.coverees.add(c4);
		Coveree c5 = new Coveree(2, "FAM1", "SPOUSE", "MED"); plan.coverees.add(c5);
		Coveree c6 = new Coveree(1, "FAM1", "PRIMARY", "BEH"); plan.coverees.add(c6);
		Coveree c7 = new Coveree(3, "FAM1", "CHILD", "BEH"); plan.coverees.add(c7);
		Coveree c8 = new Coveree(4, "FAM1", "CHILD", "MED"); plan.coverees.add(c8);
		Coveree c9 = new Coveree(1, "FAM2", "PRIMARY", "BEH"); plan.coverees.add(c9);
		Coveree c10 = new Coveree(1, "FAM2", "PRIMARY", "MED"); plan.coverees.add(c10);
		Coveree c11 = new Coveree(5, "FAM2", "CHILD", "BEH"); plan.coverees.add(c11);


		List<Pair<Integer, Integer>> results = HealthCoverageChildCountValidator.childCountInMedicalPlan(plan);
		List<Pair<Integer, Integer>> expected = new ArrayList<>();
		Pair<Integer, Integer> resultPair1 = new Pair<>(1,3);
		Pair<Integer, Integer> resultPair2 = new Pair<>(2,2);
		expected.add(resultPair1);
		expected.add(resultPair2);

		//Results contains all of the expected results, and ONLY the expected results.
		assert results.containsAll(expected);
		results.removeAll(expected);
		assert results.isEmpty();

	}
}