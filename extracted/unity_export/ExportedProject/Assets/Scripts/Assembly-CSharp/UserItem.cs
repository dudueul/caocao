using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserItem : ITransmittable, IUpsertedMarkable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_id;

	private ObscuredInt m_exp;

	private ObscuredInt m_amount;

	private ObscuredInt m_locked;

	private ObscuredInt m_level;

	private TroopStat m_stat;

	private TroopStat m_dbStat;

	private Item m_itemInfo;

	private ObscuredInt m_maxEnhanceLevel;

	private TroopStat m_baseStat;

	private TroopStat m_enhanceStat;

	private TroopStat m_dbEnhanceStat;

	private int[] m_enhancePassives;

	private int[] m_dbEnhancePassives;

	private int[] m_prevEnhancePassives;

	private int m_suggestedEnhancePassiveIndex;

	private int[] m_suggestedEnhancePassiveIDs;

	private int m_growPassiveListId;

	private ObscuredInt m_enhanceLevel;

	private ObscuredInt m_dbEnhanceLevel;

	private bool m_isAdded;

	private bool m_isUpdated;

	public Action updatedCallback;

	private ObscuredInt m_stealItemLev;

	public bool locked
	{
		get
		{
			return false;
		}
	}

	public int suggestedEnhancePassiveIndex
	{
		get
		{
			return 0;
		}
	}

	public int[] suggestedEnhancePassiveIDs
	{
		get
		{
			return null;
		}
	}

	public int EnhanceLevel
	{
		get
		{
			return 0;
		}
	}

	public int dbEnhanceLevel
	{
		get
		{
			return 0;
		}
	}

	public ObscuredBool isFairRuleAppliedToEnhanceLevel
	{
		get
		{
			return default(ObscuredBool);
		}
	}

	public TroopStat EnhanceStat
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat dbEnhanceStat
	{
		get
		{
			return default(TroopStat);
		}
	}

	[DoNotJsonfy]
	public bool isAdded
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	[DoNotJsonfy]
	public bool isUpdated
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public Action deserializeCallback { get; set; }

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

	public int count
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public TroopStat stat
	{
		get
		{
			return default(TroopStat);
		}
	}

	public TroopStat dbStat
	{
		get
		{
			return default(TroopStat);
		}
	}

	[DoNotJsonfy]
	public bool isBattleItem
	{
		get
		{
			return false;
		}
	}

	[DoNotJsonfy]
	public bool isWeaponItem
	{
		get
		{
			return false;
		}
	}

	[DoNotJsonfy]
	public bool isArmorItem
	{
		get
		{
			return false;
		}
	}

	[DoNotJsonfy]
	public bool isKitItem
	{
		get
		{
			return false;
		}
	}

	[DoNotJsonfy]
	public bool isTreasure
	{
		get
		{
			return false;
		}
	}

	[DoNotJsonfy]
	public bool isSellable
	{
		get
		{
			return false;
		}
	}

	[DoNotJsonfy]
	public Item itemInfo
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public int sellPrice
	{
		get
		{
			return 0;
		}
	}

	public float levelPercentage
	{
		get
		{
			return 0f;
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

	public int expOnCurrentLev
	{
		get
		{
			return 0;
		}
	}

	public int needHonor
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

	public int level
	{
		get
		{
			return 0;
		}
	}

	public bool isMaxLevel
	{
		get
		{
			return false;
		}
	}

	public bool CheckEnhancePassiveUnlocked(int index)
	{
		return false;
	}

	public int GetEnhancePassiveId(int index)
	{
		return 0;
	}

	public int GetDBEnhancePassiveId(int index)
	{
		return 0;
	}

	public int GetGrowPassiveListId()
	{
		return 0;
	}

	public void DeserailizeEnhance(JsonData jsonData)
	{
	}

	public void ApplyEnhanceLevel(int level, int dbLevel)
	{
	}

	public void ApplyEnhancePassive(int passiveId, int dbPassiveId, int index)
	{
	}

	public void ApplyStat()
	{
	}

	private void UpdateEnhanceStat()
	{
	}

	public bool GetPassiveEnhanceable(int index)
	{
		return false;
	}

	public void OnUpdated()
	{
	}

	public bool IsAvailableForUnit(UserUnit userUnit)
	{
		return false;
	}

	public static UserItem MakeNPCEquipableItem(ulong srl, int id, int setLevel = 1, int enhanceLevel = 0)
	{
		return null;
	}

	public static UserItem MakeBaseEquipableItem(ulong srl, int id, int setExp = 0)
	{
		return null;
	}

	public static UserItem UpdateBaseEquipableItem(UserItem userItem, int id, int setExp)
	{
		return null;
	}

	public static UserItem MakeNPCConsumableItem(ulong srl, int id, int amount)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public int UpdateLev()
	{
		return 0;
	}

	public void StealItemLev(int delta)
	{
	}

	public void DeepCopy(UserItem newItem)
	{
	}

	public bool IsEquipped()
	{
		return false;
	}

	public void UpdateFairRule(bool isEquipped, FairRule fairRule)
	{
	}

	public void UpdateFairRule(bool isEquipped)
	{
	}

	public void UpdateFairRule()
	{
	}
}
