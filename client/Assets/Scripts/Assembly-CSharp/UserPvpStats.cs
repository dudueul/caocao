using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserPvpStats : ITransmittable, iTransmitContainer
{
	private ObscuredInt m_rank;

	private ObscuredInt m_participantCnt;

	private ObscuredInt m_prevSeasonRank;

	private Tier m_previousTier;

	private RankingReward m_previousRankReward;

	private UserPvpInfos m_userPvpInfos;

	private UserPvpStat m_userPvpStat;

	private UserPvpStatSeasons m_userPvpStatSeasons;

	private UserPvpStatBattleMaps m_userPvpStatBattleMaps;

	private UserPvpStatSeasonBattleMaps m_userPvpStatSeasonBattleMaps;

	public int rank
	{
		get
		{
			return 0;
		}
	}

	public int participantCnt
	{
		get
		{
			return 0;
		}
	}

	public int prevSeasonRank
	{
		get
		{
			return 0;
		}
	}

	public Tier previousTier
	{
		get
		{
			return null;
		}
	}

	public RankingReward previousRankReward
	{
		get
		{
			return null;
		}
	}

	public UserPvpInfos pvpInfos
	{
		get
		{
			return null;
		}
	}

	public UserPvpStat pvpStat
	{
		get
		{
			return null;
		}
	}

	public UserPvpStatSeasons pvpStatSeasons
	{
		get
		{
			return null;
		}
	}

	public UserPvpStatBattleMaps pvpStatBattleMaps
	{
		get
		{
			return null;
		}
	}

	public UserPvpStatSeasonBattleMaps pvpStatSeasonBattleMaps
	{
		get
		{
			return null;
		}
	}

	public int curLeagueId
	{
		get
		{
			return 0;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeExtras(JsonData jsonData)
	{
	}

	private JsonData _deserializeFirstData(JsonData jsonData, string key)
	{
		return null;
	}

	public bool IsSeasonPlaying()
	{
		return false;
	}

	public League GetLeague()
	{
		return null;
	}

	private League _getLeague(UserPvpInfo pvpInfo)
	{
		return null;
	}

	public Tier GetPrevTier()
	{
		return null;
	}

	public Tier GetTier()
	{
		return null;
	}

	private Tier _getTier(UserPvpInfo pvpInfo)
	{
		return null;
	}

	public Tier GetNextTier()
	{
		return null;
	}

	public float GetSeasonRankPercent(int curRank, int curParticipantCnt)
	{
		return 0f;
	}

	public RankingReward GetSeasonRankReward()
	{
		return null;
	}

	public int GetStatCount(EnumData.PvpStatType statType)
	{
		return 0;
	}

	public bool HasPreviousSeasonReward()
	{
		return false;
	}
}
