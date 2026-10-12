using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class UserSpells : PermanentBase
{
	public List<UserSpell> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, UserSpell> m_userSpells;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, UserSpell> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserSpell get_Item(ObscuredInt id)
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

	private void RefineData(_UserSpells src)
	{
	}

	public static void Load(ref UserSpells res)
	{
	}
}
