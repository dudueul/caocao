using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildWarBattleHistoryInfo : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_seasonKey;

	private ObscuredInt m_seasonDetailKey;

	private ObscuredInt m_p_battleworld_id;

	private ObscuredInt m_p_guildwar_town_id;

	private ObscuredULong m_guild_id;

	private ObscuredInt m_clan_db_shard;

	private ObscuredInt m_world_id;

	private ObscuredInt m_history_result;

	private DateTime m_regDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public int seasonKey
	{
		get
		{
			return 0;
		}
	}

	public int seasonDetailKey
	{
		get
		{
			return 0;
		}
	}

	public int pBattleWorldId
	{
		get
		{
			return 0;
		}
	}

	public int pGuildwarTownId
	{
		get
		{
			return 0;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public int clanDbShard
	{
		get
		{
			return 0;
		}
	}

	public int worldId
	{
		get
		{
			return 0;
		}
	}

	public int historyResult
	{
		get
		{
			return 0;
		}
	}

	public DateTime regDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
