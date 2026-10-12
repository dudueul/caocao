using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserRuneSlot : ITransmittable, IUpsertedMarkable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_unitId;

	private ObscuredInt m_slotIndex;

	private RuneItemSlot[] runes;

	public UserUnit userUnit;

	private ObscuredInt m_runeSetPassiveListId;

	private ObscuredInt m_dbRuneSetPassiveListId;

	private ObscuredInt m_runeSetId;

	private bool m_isAdded;

	private bool m_isUpdated;

	public Action OnRuneUpdated;

	private Action deserializeCallback__BackingField;

	public bool useRuneFilterMode
	{
		get
		{
			return false;
		}
	}

	public int runeSetPassiveListId
	{
		get
		{
			return 0;
		}
	}

	public int equippedRuneSetPassiveListId
	{
		get
		{
			return 0;
		}
	}

	public int dbRuneSetPassiveListId
	{
		get
		{
			return 0;
		}
	}

	public bool runeSetActivated
	{
		get
		{
			return false;
		}
	}

	public bool equippedRuneSetActivated
	{
		get
		{
			return false;
		}
	}

	public int runeSetId
	{
		get
		{
			return 0;
		}
	}

	public ulong Srl
	{
		get
		{
			return 0uL;
		}
	}

	public int Index
	{
		get
		{
			return 0;
		}
	}

	public ulong UserId
	{
		get
		{
			return 0uL;
		}
	}

	public int UnitId
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

	public TroopStat totalTroopStat
	{
		get
		{
			return default(TroopStat);
		}
	}

	public event Action deserializeCallback
	{
		add
		{
		}
		remove
		{
		}
	}

	public int GetTotalRes(EnumData.UnitResType type)
	{
		return 0;
	}

	public int GetExpectedTotalRes(UserUnit userUnit, EnumData.UnitResType type)
	{
		return 0;
	}

	public bool IsEquipped(int id, int passiveId)
	{
		return false;
	}

	public bool IsEquippedLike(int type, int mainStat, int minTier)
	{
		return false;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	public void InvalidateRuneItemSlot(int id, int passive, int exp, int idxBaseZero)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void CheckRuneSet()
	{
	}

	public RuneItemSlot GetRune(int idxBaseOne)
	{
		return null;
	}

	public RuneItemSlot GetEquippedRune(int idxBaseOne)
	{
		return null;
	}

	public void UpdateFairRule(FairRule fairRule)
	{
	}

	public bool IsEquippedAny(int level)
	{
		return false;
	}

	public int GetExpectedRuneSetIdForNxLog(int slotId, int runeId)
	{
		return 0;
	}
}
