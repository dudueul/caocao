using System.Collections.Generic;

public class SkillGroup
{
	private List<ResultPerUnit> m_resultPerUnit;

	public EnumData.Skills skillID { get; set; }

	public List<ResultPerUnit> resultPerUnits
	{
		get
		{
			return null;
		}
	}

	public int hitCount
	{
		get
		{
			return 0;
		}
	}

	public int castingCount
	{
		get
		{
			return 0;
		}
	}

	public void AddResultPerUnit(ResultPerUnit resultPerUnit)
	{
	}
}
