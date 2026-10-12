using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Rewards : PermanentBase
{
	public List<Reward> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Reward> m_rewards;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Reward> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Reward get_Item(ObscuredInt id)
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

	private void RefineData(_Rewards src)
	{
	}

	public static void Load(ref Rewards res)
	{
	}
}
