using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ArenaGetBonuses : PermanentBase
{
	public List<ArenaGetBonus> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ArenaGetBonus> m_arenaGetBonuses;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ArenaGetBonus> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ArenaGetBonus get_Item(ObscuredInt id)
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

	private void RefineData(_ArenaGetBonuses src)
	{
	}

	public static void Load(ref ArenaGetBonuses res)
	{
	}
}
