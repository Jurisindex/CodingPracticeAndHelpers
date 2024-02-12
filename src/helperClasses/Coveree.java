package helperClasses;

public class Coveree
{
	//member_id int, family_id String, relation String, coverage String
	public int memberId;
	public String familyId;
	public String relation;
	public String coverage;

	public Coveree(int memberId, String familyId, String relation, String coverage )
	{
		this.memberId = memberId;
		this.familyId = familyId;
		this.relation = relation;
		this.coverage = coverage;
	}
}