using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Guilds : PermanentBase
{
	public List<Guild> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Guild> m_guilds;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Guild> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Guild get_Item(ObscuredInt id)
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

	private void RefineData(_Guilds src)
	{
	}

	public static void Load(ref Guilds res)
	{
	}
}
