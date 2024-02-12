package examSources;

/*
Input structure:
( member_id int, family_id String, relation String, coverage String);

Input values:
( 5, 'FAM2', 'CHILD', 'MED'),
( 2, 'FAM1', 'SPOUSE', 'BEH'),
( 3, 'FAM1', 'CHILD', 'MED'),
( 1, 'FAM1', 'PRIMARY', 'MED'),
( 2, 'FAM1', 'SPOUSE', 'MED'),
( 1, 'FAM1', ' PRIMARY ', 'BEH'),
( 3, 'FAM1', 'CHILD', 'BEH'),
( 4, 'FAM1', 'CHILD', 'MED'),
( 1, 'FAM2', ' PRIMARY ', 'BEH'),
( 1, 'FAM2', 'PRIMARY', 'MED'),
( 5, 'FAM2', 'CHILD', 'BEH')

Expected output
(member_id, childCount)
 */

import helperClasses.Pair;
import helperClasses.Coveree;
import helperClasses.MedicalPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthCoverageChildCountValidator
{
	public static List<Pair<Integer, Integer>> childCountInMedicalPlan(MedicalPlan plan)
	{
		Map<String, List<Pair<String, Integer>>> familyAndMembersMap = new HashMap();
		Map<Integer, Integer> parentChildrenCount = new HashMap<>();

		populateFamilyMembershipAndParentMaps(plan, familyAndMembersMap, parentChildrenCount);
		countChildrenForEachParent(familyAndMembersMap, parentChildrenCount);

		//Generate pairs to return based on above results.
		List<Pair<Integer, Integer>> memberChildrenMapping = new ArrayList<>();
		for (Map.Entry e : parentChildrenCount.entrySet())
		{
			Integer parentId = (Integer) e.getKey();
			Integer childrenCount = (Integer) e.getValue();
			memberChildrenMapping.add(new Pair<>(parentId, childrenCount));
		}

		//Return that result set
		return memberChildrenMapping;
	}

	private static void countChildrenForEachParent(Map<String, List<Pair<String, Integer>>> familyAndMembersMap, Map<Integer, Integer> parentChildrenCount)
	{
		//Go through each family, and tally each adult's relation to those children.
		for (Map.Entry e : familyAndMembersMap.entrySet())
		{
			//Get the coverees in the family.
			List<Pair<String, Integer>> covereesInFamily = (List<Pair<String, Integer>>) e.getValue();
			List<Integer> parentsToIncrement = new ArrayList<>();
			Integer amountToIncrement = 0;

			for (Pair<String, Integer> c : covereesInFamily)
			{
				//See which parents are in this family
				Integer memberId = c.getVal();
				if (parentChildrenCount.get(memberId) != null)
				{
					parentsToIncrement.add(memberId);
				}
				else    //it's a child
				{
					//Count how many children are in the family
					amountToIncrement++;
				}
			}

			//Converge those numbers.
			for (Integer memberId : parentsToIncrement)
			{
				Integer childrenSoFar = parentChildrenCount.get(memberId);
				childrenSoFar += amountToIncrement;
				parentChildrenCount.put(memberId, childrenSoFar);
			}
		}
	}

	private static void populateFamilyMembershipAndParentMaps(MedicalPlan plan, Map<String, List<Pair<String, Integer>>> familyAndMembersMap, Map<Integer, Integer> parentChildrenCount)
	{
		//Output format:
		//Family 2 : {Child 5, Primary 1}
		//Family 1 : {Child 4, Child 3, Spouse 2, Primary 1}

		for (Coveree c : plan.coverees)
		{
			//Add all parents to the parent map.
			if (c.relation.equals("PRIMARY") || c.relation.equals("SPOUSE"))
			{
				parentChildrenCount.put(c.memberId, 0);    //We will use this information later.
			}

			//Figure out their family. Check if there, if not, add it, and themselves in the List.
			String family = c.familyId;
			List<Pair<String, Integer>> familyList = familyAndMembersMap.get(family);
			if (familyList != null) //Family exists
			{
				//If there, get the list, modify it with themselves if not already in it, and put it back.
				if (!familyList.contains(new Pair<>(c.relation, c.memberId)))
				{
					familyList.add(new Pair(c.relation, c.memberId));
					familyAndMembersMap.put(family, familyList);
				}
			}
			else
			{
				//update the family list with the newly found member
				familyList = new ArrayList<>();
				familyList.add(new Pair(c.relation, c.memberId));
				familyAndMembersMap.put(family, familyList);
			}
		}
	}
}