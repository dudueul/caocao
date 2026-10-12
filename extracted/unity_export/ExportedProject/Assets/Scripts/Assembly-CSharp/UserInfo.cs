using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserInfo : ITransmittable
{
	private const ulong initializeUid = 512uL;

	protected ObscuredULong m_uid;

	protected ObscuredString m_authid;

	protected ObscuredInt m_exp;

	protected ObscuredInt m_activityPoint;

	protected ObscuredInt m_level;

	protected ObscuredInt m_gold;

	protected ObscuredInt m_chargedGold;

	protected ObscuredInt m_freeGold;

	protected ObscuredInt m_silver;

	protected ObscuredInt m_chargedSilver;

	protected ObscuredInt m_freeSilver;

	protected ObscuredInt m_food;

	protected ObscuredInt m_stamp;

	protected ObscuredInt m_fame;

	protected ObscuredInt m_maxFoodLev;

	protected ObscuredInt m_maxSilverLev;

	protected ObscuredInt m_recentMap;

	protected ObscuredInt m_kingUnit;

	protected ObscuredInt m_scenarioPlayCnt;

	protected ObscuredInt m_multiPlayCnt;

	protected ObscuredInt m_multiPlayWin;

	protected ObscuredInt m_multiPlayLose;

	protected ObscuredInt m_multiPlayDailyCnt;

	protected DateTime m_multiPlayUpdateDate;

	protected DateTime m_protectedLimitTime;

	protected ObscuredInt m_exploit;

	protected ObscuredInt m_shamPoint;

	protected ObscuredInt m_shamWin;

	protected ObscuredInt m_shamLose;

	protected ObscuredInt m_shamReward;

	protected ObscuredInt m_vipPoint;

	protected ObscuredInt m_vipLevel;

	protected ObscuredString m_nationName;

	protected ObscuredString m_nation;

	protected ObscuredInt m_trace;

	protected ObscuredInt m_faceUnitId;

	protected ObscuredInt m_skinItemId;

	protected ObscuredULong m_representGroup;

	protected ObscuredInt m_followCnt;

	protected DateTime m_lastPlayed;

	protected DateTime m_regDate;

	protected DateTime m_updDate;

	protected DateTime m_lordEffectBegin;

	protected int m_attendSeq;

	protected EnumData.AttendType m_attendType;

	protected string m_lastAttendDate;

	protected int m_eventAttendId;

	protected int m_eventAttendSeq;

	protected string m_eventAttendDate;

	protected int m_consectiveAttendSeq;

	protected string m_consectiveAttendDate;

	protected ObscuredInt m_annihilationTicket;

	protected ObscuredULong m_anniTicketChargedTime;

	protected EnumData.UserStatus m_userStatus;

	protected DateTime m_returnDate;

	protected ObscuredInt m_annihilationPrevRank;

	protected int m_specialAttendSeq;

	protected EnumData.SpecialAttendType m_specialAttendType;

	private ObscuredBool m_isFirstName;

	public bool deserialized;

	public Action deserializeCallback;

	public virtual ulong uid
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public string authid
	{
		get
		{
			return null;
		}
	}

	public int gold
	{
		get
		{
			return 0;
		}
	}

	public int chargedGold
	{
		get
		{
			return 0;
		}
	}

	public int freeGold
	{
		get
		{
			return 0;
		}
	}

	public int chargedSilver
	{
		get
		{
			return 0;
		}
	}

	public int freeSilver
	{
		get
		{
			return 0;
		}
	}

	public int silver
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int food
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int stamp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int fame
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int exp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int activityPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kingUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxFoodLev
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxSilverLev
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int scenarioPlayCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int multiPlayCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int multiPlayWin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int multiPlayLose
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public DateTime protectedLimitTime
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public int exploit
	{
		get
		{
			return 0;
		}
	}

	public int shamPoint
	{
		get
		{
			return 0;
		}
	}

	public int shamWin
	{
		get
		{
			return 0;
		}
	}

	public int shamLose
	{
		get
		{
			return 0;
		}
	}

	public int shamReward
	{
		get
		{
			return 0;
		}
	}

	public float shamWinningRate
	{
		get
		{
			return 0f;
		}
	}

	public int vipPoint
	{
		get
		{
			return 0;
		}
	}

	public int vipLevel
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	public int trace
	{
		get
		{
			return 0;
		}
	}

	public bool isFirstName
	{
		get
		{
			return false;
		}
	}

	public string nationName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string nation
	{
		get
		{
			return null;
		}
	}

	public DateTime lordEffectBegin
	{
		get
		{
			return default(DateTime);
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

	public EnumData.AttendType attendType
	{
		get
		{
			return EnumData.AttendType.Invalid;
		}
	}

	public int attendSeq
	{
		get
		{
			return 0;
		}
	}

	public EnumData.SpecialAttendType specialAttendType
	{
		get
		{
			return EnumData.SpecialAttendType.None;
		}
	}

	public int specialAttendSeq
	{
		get
		{
			return 0;
		}
	}

	public DateTime lastAttendDateAdd1Day
	{
		get
		{
			return default(DateTime);
		}
	}

	public string lastAttendDate
	{
		get
		{
			return null;
		}
	}

	public int eventAttendId
	{
		get
		{
			return 0;
		}
	}

	public int eventAttendSeq
	{
		get
		{
			return 0;
		}
	}

	public DateTime eventAttendDateAdd1Day
	{
		get
		{
			return default(DateTime);
		}
	}

	public int consecutiveAttendSeq
	{
		get
		{
			return 0;
		}
	}

	public string consecutiveAttendDate
	{
		get
		{
			return null;
		}
	}

	public int annihilationTicket
	{
		get
		{
			return 0;
		}
	}

	public ulong anniTicketChargedTime
	{
		get
		{
			return 0uL;
		}
	}

	[DoNotJsonfy]
	public TimeSpan time2nextTicketCharge
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public EnumData.UserStatus userStatus
	{
		get
		{
			return (EnumData.UserStatus)0;
		}
	}

	public DateTime returnDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int VisibleFaceUnitID
	{
		get
		{
			return 0;
		}
	}

	public virtual int FaceUnitID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public virtual int SkinItemID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public ulong RepresentGroup
	{
		get
		{
			return 0uL;
		}
	}

	public int FollowCnt
	{
		get
		{
			return 0;
		}
	}

	public DateTime LastPlayed
	{
		get
		{
			return default(DateTime);
		}
	}

	public int annihilationPrevRank
	{
		get
		{
			return 0;
		}
	}

	public string goldWithComma
	{
		get
		{
			return null;
		}
	}

	public string silverWithComma
	{
		get
		{
			return null;
		}
	}

	public string foodWithComma
	{
		get
		{
			return null;
		}
	}

	public string stampWithComma
	{
		get
		{
			return null;
		}
	}

	public string vipGaugeText
	{
		get
		{
			return null;
		}
	}

	public float vipGaugeFillAmount
	{
		get
		{
			return 0f;
		}
	}

	public bool isMaxLevel
	{
		get
		{
			return false;
		}
	}

	public int baseExpSum
	{
		get
		{
			return 0;
		}
	}

	public int needExp
	{
		get
		{
			return 0;
		}
	}

	public int expOnCurrentLevToShow
	{
		get
		{
			return 0;
		}
	}

	public UserInfo()
	{
	}

	public UserInfo(ulong uid)
	{
	}

	private void updateLevel(int level)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void UpdateRecentMap(int scenarioID)
	{
	}

	public int GetOwnedGoods(EnumData.GoodsType goodsType)
	{
		return 0;
	}

	public bool CanUseGoods(EnumData.GoodsType type, int amount)
	{
		return false;
	}

	public bool IsKing(int unitID)
	{
		return false;
	}

	public void UpgradeGoodsMax(EnumData.TownBuildingType buildingType)
	{
	}

	public int multiPlayDailyCnt()
	{
		return 0;
	}
}
