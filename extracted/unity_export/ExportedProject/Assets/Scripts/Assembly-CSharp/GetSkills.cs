using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GetSkills : PermanentBase
{
	public List<GetSkill> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GetSkill> m_getSkills;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GetSkill> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GetSkill get_Item(ObscuredInt id)
	{
		return null;
	}

	public List<ObscuredInt> ids
	{
		get
		{
			return null;
		}
	}

	private void RefineData(_GetSkills src)
	{
	}

	public static void Load(ref GetSkills res)
	{
	}
}
