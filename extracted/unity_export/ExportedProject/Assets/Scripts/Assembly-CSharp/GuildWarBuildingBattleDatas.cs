using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class GuildWarBuildingBattleDatas : PermanentBase
{
	public List<GuildWarBuildingBattleData> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, GuildWarBuildingBattleData> m_guildWarBuildingBattleDatas;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, GuildWarBuildingBattleData> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarBuildingBattleData get_Item(ObscuredInt id)
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

	private void RefineData(_GuildWarBuildingBattleDatas src)
	{
	}

	public static void Load(ref GuildWarBuildingBattleDatas res)
	{
	}
}
