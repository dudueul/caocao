using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildInfo : ITransmittable
{
	private ObscuredULong m_guildId;

	private ObscuredULong m_owner;

	private ObscuredString m_name;

	private ObscuredInt m_exp;

	private ObscuredInt m_level;

	private ObscuredInt m_activityPoint;

	private ObscuredString m_motd;

	private ObscuredString m_recruitMsg;

	private ObscuredChar m_acType;

	private ObscuredInt m_rankLimit;

	private ObscuredInt m_userNum;

	private ObscuredBool m_autoApplySiegeWar;

	private ObscuredInt m_guildwar_rank;

	private DateTime m_guildwar_rank_expire;

	private DateTime m_updDate;

	private DateTime m_regDate;

	private ObscuredInt m_markTextureIndex;

	private ObscuredInt m_guildWarMarkTextureIndex;

	private ObscuredInt m_guildwar_adjacent_season_key;

	private ObscuredInt m_guildwar_adjacent_season_detail_key;

	private ObscuredInt m_guildwar_adjacent_battleworld_id;

	private ObscuredInt m_guildwar_adjacent_town_id;

	public JsonData emblem;

	public JsonData guildwar_emblem;

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong owner
	{
		get
		{
			return 0uL;
		}
	}

	public string name
	{
		get
		{
			return null;
		}
	}

	public int exp
	{
		get
		{
			return 0;
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
	}

	public int activityPoint
	{
		get
		{
			return 0;
		}
	}

	public string motd
	{
		get
		{
			return null;
		}
	}

	public string recruitMsg
	{
		get
		{
			return null;
		}
	}

	public char acType
	{
		get
		{
			return '\0';
		}
	}

	public int guildwarRank
	{
		get
		{
			return 0;
		}
	}

	public DateTime guildwarRankExpire
	{
		get
		{
			return default(DateTime);
		}
	}

	public int rankLimit
	{
		get
		{
			return 0;
		}
	}

	public int userNum
	{
		get
		{
			return 0;
		}
	}

	public bool autoApplySiegeWar
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public EnumData.GuildAcceptType acceptType { get; protected set; }

	public int markTextureIndex
	{
		get
		{
			return 0;
		}
	}

	public int oriMarkTextureIndex
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

	public DateTime updDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int guildwar_adjacent_season_key
	{
		get
		{
			return 0;
		}
	}

	public int guildwar_adjacent_season_detail_key
	{
		get
		{
			return 0;
		}
	}

	public int guildwar_adjacent_battleworld_id
	{
		get
		{
			return 0;
		}
	}

	public int guildwar_adjacent_town_id
	{
		get
		{
			return 0;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}
}
