using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ExpandPassiveSkills : PermanentBase
{
	public List<ExpandPassiveSkill> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ExpandPassiveSkill> m_expandPassiveSkills;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ExpandPassiveSkill> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ExpandPassiveSkill get_Item(ObscuredInt id)
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

	private void RefineData(_ExpandPassiveSkills src)
	{
	}

	public static void Load(ref ExpandPassiveSkills res)
	{
	}
}
