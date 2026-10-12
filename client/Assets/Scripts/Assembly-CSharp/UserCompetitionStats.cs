using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserCompetitionStats : ITransmittable, iTransmitContainer
{
	private ObscuredInt m_rank;

	private ObscuredInt m_participantCnt;

	private ObscuredInt m_prevWeekRank;

	private ObscuredInt m_prevWeekPoint;

	private ObscuredInt m_prevWeekTierGrade;

	private ObscuredInt m_prevPlayCount;

	private Tier m_previousTier;

	private RankingReward m_previousRankReward;

	private UserCompetitionInfos m_userCompetitionInfos;

	private UserCompetitionStat m_userCompetitionStat;

	private UserCompetitionStatSeasons m_userCompetitionStatSeasons;

	private UserCompetitionStatBattleMaps m_userCompetitionStatBattleMaps;

	private UserCompetitionStatSeasonBattleMaps m_userCompetitionStatSeasonBattleMaps;

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

	public int prevWeekRank
	{
		get
		{
			return 0;
		}
	}

	public int prevWeekPoint
	{
		get
		{
			return 0;
		}
	}

	public int prevWeekTierGrade
	{
		get
		{
			return 0;
		}
	}

	public int prevPlayCount
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

	public UserCompetitionInfos competitionInfos
	{
		get
		{
			return null;
		}
	}

	public UserCompetitionStat competitionStat
	{
		get
		{
			return null;
		}
	}

	public UserCompetitionStatSeasons competitionStatSeasons
	{
		get
		{
			return null;
		}
	}

	public UserCompetitionStatBattleMaps competitionStatBattleMaps
	{
		get
		{
			return null;
		}
	}

	public UserCompetitionStatSeasonBattleMaps competitionStatSeasonBattleMaps
	{
		get
		{
			return null;
		}
	}

	public bool isPublicTier
	{
		get
		{
			return false;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
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

	public bool IsFirstSeason()
	{
		return false;
	}

	public bool IsSeasonPlaying()
	{
		return false;
	}

	public Tier GetTier()
	{
		return null;
	}

	private bool IsTierPlacementTest()
	{
		return false;
	}

	public Tier GetNextTier()
	{
		return null;
	}

	public Tier GetPrevTier()
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

	public int GetStatCount(EnumData.CompetitionStatType statType)
	{
		return 0;
	}

	public bool HasReward()
	{
		return false;
	}

	public UserCompetitionStatSeasonBattleMap GetStatSeasonBattleMap(int competitionId)
	{
		return null;
	}
}
