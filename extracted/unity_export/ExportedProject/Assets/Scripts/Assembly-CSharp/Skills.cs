using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Skills : PermanentBase
{
	public List<Skill> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Skill> m_skills;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Skill> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Skill get_Item(ObscuredInt id)
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

	private void RefineData(_Skills src)
	{
	}

	public static void Load(ref Skills res)
	{
	}
}
