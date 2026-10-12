using System.Collections.Generic;
using GuildWar;
using LitJson;

public class GuildWarLobbyData
{
	private static GuildWarLobbyData s_instance;

	private GuildWarUserBaseDatas m_user_basedatas;

	private GuildWarSchedules m_schedules;

	private GuildWarScheduleDetailGroups m_schedule_detail_groups;

	private GuildWarScheduleDetails m_schedule_details;

	private GuildWarWorldTowns m_towns;

	private GuildWarAttends m_attends;

	private GuildWarGuilds m_guilds;

	private GuildWarRankPoints m_guild_activity_points;

	private GuildWarBattleJoins m_battle_joins;

	private GuildWarBattleHistoryInfos m_battleHistoryInfos;

	private GuildWarTownColors m_townColors;

	private bool m_isGuildMember;

	private float m_latestRequestTimeGetLobbyData;

	private float m_latestRequestTimeGetBattleHistories;

	public static GuildWarLobbyData instance
	{
		get
		{
			return null;
		}
	}

	public static bool hasInstance
	{
		get
		{
			return false;
		}
	}

	public GuildWarUserBaseDatas user_basedatas
	{
		get
		{
			return null;
		}
	}

	public GuildWarSchedules schedules
	{
		get
		{
			return null;
		}
	}

	public GuildWarScheduleDetailGroups schedule_detail_groups
	{
		get
		{
			return null;
		}
	}

	public GuildWarScheduleDetails schedule_details
	{
		get
		{
			return null;
		}
	}

	public GuildWarWorldTowns towns
	{
		get
		{
			return null;
		}
	}

	public GuildWarAttends attends
	{
		get
		{
			return null;
		}
	}

	public GuildWarGuilds guilds
	{
		get
		{
			return null;
		}
	}

	public GuildWarRankPoints guild_activity_points
	{
		get
		{
			return null;
		}
	}

	public GuildWarBattleJoins battle_joins
	{
		get
		{
			return null;
		}
	}

	public GuildWarBattleHistoryInfos battleHistoryInfos
	{
		get
		{
			return null;
		}
	}

	public GuildWarTownColors townColors
	{
		get
		{
			return null;
		}
	}

	public bool isGuildMember
	{
		get
		{
			return false;
		}
	}

	public float latestRequestTimeGetLobbyData
	{
		get
		{
			return 0f;
		}
	}

	public float latestRequestTimeGetBattleHistories
	{
		get
		{
			return 0f;
		}
	}

	private bool CommonCheck(JsonData response)
	{
		return false;
	}

	private void RequestData(bool my_user_base_datas, bool schedules, int detail_battleworld, int detail_town, int rough_battleworld, bool my_details, HttpRequest.ResponseCallback callback = null)
	{
	}

	public void GetUserDeckData(HttpRequest.ResponseCallback callback = null)
	{
	}

	public void GetGuildData()
	{
	}

	public void RequestLobbyData(HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestMyBattleWorld(int seasonKey, int seasonDetailKey, HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestRoughBattleWorld(int seasonKey, int seasonDetailKey, int battleWorldId, int townId, HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestDetailTown(int seasonKey, int seasonDetailKey, int battleWorldId, int townId, HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestDiscardTownStart(int seasonKey, int seasonDetailKey, int battleworldId, int townId, HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestDiscardTown(int seasonKey, int seasonDetailKey, int battleworldId, int townId, string hashValue, HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestUpdateUserBaseData(HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestSiegeAttend(int seasonKey, int seasonDetailKey, int battleworldId, int townId, HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestBattleHistories(int seasonKey, HttpRequest.ResponseCallback callback = null)
	{
	}

	public void RequestGuildInfos(List<ulong> guildList, HttpRequest.ResponseCallback callback = null)
	{
	}

	public HttpRequest.HttpRequestContext CreateRequestAddGuildWarGold(int addGuildWarGold, HttpRequest.ResponseCallback callback = null)
	{
		return null;
	}
}
