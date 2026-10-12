using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserTown : ITransmittable
{
	private static PermanentData ms_pd;

	private ObscuredULong m_uid;

	private ObscuredULong m_srl;

	private ObscuredInt m_id;

	private ObscuredULong m_owner;

	private ObscuredULong m_ownerAuthID;

	private ObscuredInt m_exp;

	private ObscuredInt m_durability;

	private ObscuredInt m_farm;

	private ObscuredInt m_market;

	private ObscuredInt m_foodStorage;

	private ObscuredInt m_silverStorage;

	private ObscuredInt m_depository;

	private ObscuredInt m_mayorUnitId;

	private ObscuredBool m_isCapital;

	private DateTime m_foodHarvestDateTime;

	private DateTime m_silverHarvestDateTime;

	private ObscuredInt m_keepingSilver;

	private ObscuredInt m_keepingFood;

	private EnumData.townStatusType m_townStatus;

	private DateTime m_limitTime;

	private EnumData.TacticsType m_tacticType;

	private ObscuredInt m_deckId;

	private ObscuredString m_townName;

	private ObscuredString m_regionName;

	private ObscuredFloat m_townOrderValue;

	private ObscuredULong m_shamUserId;

	private DateTime m_lastShamBattle;

	private ObscuredInt m_shamTimeBonus;

	private ObscuredBool m_virtualUser;

	private ObscuredInt m_plunderableFood;

	private ObscuredInt m_plunderableSilver;

	private ObscuredInt m_popularity;

	private DateTime m_lastPopularityDecreased;

	private DateTime m_lastTributeRecieved;

	private ObscuredBool m_riot_occured;

	private MatchUp m_matchUp;

	private string m_localizedTownName;

	private string m_localizedRegionName;

	private static PermanentData pd
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public ulong uid
	{
		get
		{
			return 0uL;
		}
	}

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public int id
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int durability
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int farm
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int market
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int foodStorage
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silverStorage
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int depository
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public EnumData.TacticsType tacticType
	{
		get
		{
			return EnumData.TacticsType.A;
		}
		set
		{
		}
	}

	public int deckId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public Town townInfo { get; private set; }

	public int mayorUnitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isCapital
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public DateTime foodHarvestDateTime
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public DateTime silverHarvestDateTime
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public int keepingSilver
	{
		get
		{
			return 0;
		}
	}

	public int keepingFood
	{
		get
		{
			return 0;
		}
	}

	public MatchUp matchUp
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public ulong owner
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public EnumData.townStatusType townStatus
	{
		get
		{
			return EnumData.townStatusType.none;
		}
		set
		{
		}
	}

	public int remainSec
	{
		get
		{
			return 0;
		}
	}

	public DateTime limitTime
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public bool virtualUser
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int plunderableFood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int plunderableSilver
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool IsMatchingExpired
	{
		get
		{
			return false;
		}
	}

	public bool isUnderAttack
	{
		get
		{
			return false;
		}
	}

	public string townName
	{
		get
		{
			return null;
		}
	}

	public string localizedTownName
	{
		get
		{
			return null;
		}
	}

	public string regionName
	{
		get
		{
			return null;
		}
	}

	public string localizedRegionName
	{
		get
		{
			return null;
		}
	}

	public float townOrderValue
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool isCastle
	{
		get
		{
			return false;
		}
	}

	public ulong shamOpponentId
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public DateTime lastShamBattle
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public int shamTimeBonus
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	[DoNotJsonfy]
	public TimeSpan shamBattleCoolTime
	{
		get
		{
			return default(TimeSpan);
		}
	}

	[DoNotJsonfy]
	public string shamBattleCoolTimeStr
	{
		get
		{
			return null;
		}
	}

	public bool isOnCoolTime
	{
		get
		{
			return false;
		}
	}

	public IUserDataBase ownerData
	{
		get
		{
			return null;
		}
	}

	public UserInfo mayorInfo
	{
		get
		{
			return null;
		}
	}

	public UserUnit mayerUnit
	{
		get
		{
			return null;
		}
	}

	public int foodHarvestBonus
	{
		get
		{
			return 0;
		}
	}

	public int silverHarvestBonus
	{
		get
		{
			return 0;
		}
	}

	public int foodStorageBonus
	{
		get
		{
			return 0;
		}
	}

	public int silverStorageBonus
	{
		get
		{
			return 0;
		}
	}

	public int safeBonus
	{
		get
		{
			return 0;
		}
	}

	public int foodPlunderBonus
	{
		get
		{
			return 0;
		}
	}

	public int silverPlunderBonus
	{
		get
		{
			return 0;
		}
	}

	public int popularity
	{
		get
		{
			return 0;
		}
	}

	public DateTime lastPopularityDecreased
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime lastTributeRecieved
	{
		get
		{
			return default(DateTime);
		}
	}

	public bool riotOccured
	{
		get
		{
			return false;
		}
	}

	public int maxPopularity
	{
		get
		{
			return 0;
		}
	}

	public float popularityPercent
	{
		get
		{
			return 0f;
		}
	}

	public int popularityGrade
	{
		get
		{
			return 0;
		}
	}

	public string popularityGradeState
	{
		get
		{
			return null;
		}
	}

	public UserTown()
	{
	}

	public UserTown(ulong uid, ulong srl, int townId, ulong owner)
	{
	}

	public int GetLeaderEffectBonus(EnumData.TownBuildingType buildingType)
	{
		return 0;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsOwnTerritory()
	{
		return false;
	}

	public bool IsNPCTown()
	{
		return false;
	}

	public void UpgradeBuilding(EnumData.TownBuildingType buildingType)
	{
	}

	public int GetBuildingLevel(EnumData.TownBuildingType buildingType)
	{
		return 0;
	}

	public int GetFunctionId(EnumData.TownBuildingType buildingType)
	{
		return 0;
	}

	public int GetHarvestAmount(EnumData.GoodsType type)
	{
		return 0;
	}

	public int GetHarverstableRate(EnumData.GoodsType type)
	{
		return 0;
	}

	public int GetSafePct()
	{
		return 0;
	}

	public int GetPlunderableAmount(EnumData.GoodsType type)
	{
		return 0;
	}

	public int GetTownStorageMax(EnumData.GoodsType type)
	{
		return 0;
	}

	public bool isMaxLevel(EnumData.TownBuildingType buildingType)
	{
		return false;
	}

	public int GetMinPopularityForReward()
	{
		return 0;
	}

	public int GetPopularityRewardTerm()
	{
		return 0;
	}

	public TimeSpan GetTributeRemainingTime()
	{
		return default(TimeSpan);
	}

	public void UpdatePopularity(int deltaPopularity)
	{
	}

	public int CalculatePopularityRecoverAvailableCount(EnumData.PopularityRecoveryType recoverType)
	{
		return 0;
	}

	public int GetPopularityRecoverCost(EnumData.PopularityRecoveryType recoverType, int count)
	{
		return 0;
	}

	public int GetDefenceSlotCount()
	{
		return 0;
	}
}
