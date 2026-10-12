using System;
using System.Collections.Generic;
using LitJson;

public class GuildSiegeWarLeague : ITransmittable
{
	private ulong m_leagueId;

	private string m_limitDay;

	private int m_tier;

	private ulong[] m_guildIds;

	private List<ulong> m_ids;

	private Dictionary<ulong, GuildSiegeWarWar> m_id2War;

	private List<ulong> m_atkIds;

	private Dictionary<ulong, GuildSiegeWarBattleAtk> m_id2AtkBattle;

	private List<ulong> m_defIds;

	private Dictionary<ulong, GuildSiegeWarBattleDef> m_id2DefBattle;

	private Dictionary<int, GuildSiegeWarWar> m_order2AtkWar;

	private Dictionary<int, GuildSiegeWarWar> m_order2DefWar;

	private List<GuildSiegeWarRankInfo> m_atkRanking;

	private List<GuildSiegeWarRankInfo> m_defRanking;

	public Action DeserializeLeagueCallback;

	public Action DeserializeWarCallback;

	public Action DeserializeAtkBattleCallback;

	public Action DeserializeDefBattleCallback;

	public ulong leagueId
	{
		get
		{
			return 0uL;
		}
	}

	public string limitDay
	{
		get
		{
			return null;
		}
	}

	public int tier
	{
		get
		{
			return 0;
		}
	}

	public ulong[] guildIds
	{
		get
		{
			return null;
		}
	}

	public ulong guildId01
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guildId02
	{
		get
		{
			return 0uL;
		}
	}

	public List<GuildSiegeWarRankInfo> atkRanking
	{
		get
		{
			return null;
		}
	}

	public List<GuildSiegeWarRankInfo> defRanking
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeWarWar get_Item(ulong id)
	{
		return null;
	}

	public GuildSiegeWarWar GetAtkWar(int warOrder)
	{
		return null;
	}

	public GuildSiegeWarWar GetDefWar(int warOrder)
	{
		return null;
	}

	public ulong GetEnemyGuildId()
	{
		return 0uL;
	}

	protected override void DeserializeImpl(JsonData data)
	{
	}

	public void DeserializeLeague(JsonData data)
	{
	}

	private void SetLeagueChanged()
	{
	}

	public void DeserializeWars(JsonData data)
	{
	}

	public void DeserializeAtks(JsonData atksData)
	{
	}

	public void DeserializeDefs(JsonData defsData)
	{
	}

	public void UpdateMemberRankInfo()
	{
	}
}
