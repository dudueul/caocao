using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;

public class GuildSiegeWarManager
{
	public delegate void GuildSiegeWarScheduleDelegate(GuildSiegeWarSchedule schedule);

	private GuildSiegeWarSchedule m_schedule;

	private GuildSiegeWarLeague m_league;

	public ulong leagueId;

	public ulong warId;

	public ulong atkDeckSrl;

	public ulong defDeckSrl;

	public List<int> unitList;

	public int usedType;

	public int facilityId;

	public int facilityWarSlot;

	public ulong enemyGuildId;

	public ulong enemyUserId;

	public int defencedeckSlot;

	public static GuildSiegeWarManager instance
	{
		get
		{
			return null;
		}
	}

	public int sequence
	{
		get
		{
			return 0;
		}
	}

	public bool isSetDefenceSequence
	{
		get
		{
			return false;
		}
	}

	public bool isAttackSequence
	{
		get
		{
			return false;
		}
	}

	public DateTime begin
	{
		get
		{
			return default(DateTime);
		}
	}

	public GuildSiegeWarLeague league
	{
		get
		{
			return null;
		}
	}

	public bool isInLeagueNow
	{
		get
		{
			return false;
		}
	}

	public void GetSchedule(GuildSiegeWarScheduleDelegate callback)
	{
	}

	public void Apply(Action callback)
	{
	}

	public void SetAutoApply(Action callback)
	{
	}

	[DebuggerHidden]
	public IEnumerator GetGuildSiegeWarInfo(Action callback)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator GetLeagueData()
	{
		return null;
	}

	public bool CommonCallback(JsonData response)
	{
		return false;
	}

	public void StartWar(Action callback)
	{
	}

	public void StartBattle(Action onSuccess)
	{
	}

	public void EndBattle(List<object> paramObjects, Action<JsonData> onProcessBattleEnd)
	{
	}

	public void ChangePhase(ulong warId, Action callback)
	{
	}

	public string GetTimeRemainedUntilLeagueEnd()
	{
		return null;
	}

	public string GetTimeRemainedUntilWarStart()
	{
		return null;
	}

	public string GetTimeRemainedUntilWarEnd()
	{
		return null;
	}

	public GuildSiegeWarWar GetCurrentAtkWar()
	{
		return null;
	}

	public GuildSiegeWarWar GetCurrentDefWar()
	{
		return null;
	}

	public DateTime GetWarBegin()
	{
		return default(DateTime);
	}

	public DateTime GetWarEnd()
	{
		return default(DateTime);
	}

	public bool IsWarStarted()
	{
		return false;
	}

	public bool IsWarEnded()
	{
		return false;
	}

	public bool CanAttackNow()
	{
		return false;
	}

	public int GetCurParticipants()
	{
		return 0;
	}

	public int GetTotalParticipants()
	{
		return 0;
	}

	public void UpdateMemberRankInfo()
	{
	}

	public GuildData GetEnemyGuildData()
	{
		return null;
	}

	public GuildSiegeWarAttackDeck.UsedType GetAttackUnitUsedType(GuildSiegeWarWar war)
	{
		return GuildSiegeWarAttackDeck.UsedType.Loaded;
	}
}
