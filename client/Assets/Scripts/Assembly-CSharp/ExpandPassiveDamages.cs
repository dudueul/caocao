using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ExpandPassiveDamages : PermanentBase
{
	public List<ExpandPassiveDamage> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ExpandPassiveDamage> m_expandPassiveDamages;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ExpandPassiveDamage> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ExpandPassiveDamage get_Item(ObscuredInt id)
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

	private void RefineData(_ExpandPassiveDamages src)
	{
	}

	public static void Load(ref ExpandPassiveDamages res)
	{
	}
}
