using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarInteractiveFunctions : PermanentBase
{
	public List<GuildWarInteractiveFunction> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarInteractiveFunction> m_guildWarInteractiveFunctions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarInteractiveFunction> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarInteractiveFunction get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarInteractiveFunctions src)
	{
	}

	public static void Load(ref GuildWarInteractiveFunctions res)
	{
	}
}
