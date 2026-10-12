using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class AISkills : PermanentBase
{
	public List<AISkill> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, AISkill> m_aISkills;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, AISkill> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public AISkill get_Item(ObscuredInt id)
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

	private void RefineData(_AISkills src)
	{
	}

	public static void Load(ref AISkills res)
	{
	}
}
