using CodeStage.AntiCheat.ObscuredTypes;

public class RuneItemSlot
{
	private ObscuredInt m_runeIdx;

	private ObscuredInt m_runeId;

	private TroopStat m_troopStat;

	private UserUnit.UnitRes m_unitRes;

	private ObscuredInt m_exp;

	private ObscuredInt m_level;

	private ObscuredInt m_dbLevel;

	private ObscuredInt m_passiveId;

	private ObscuredInt m_passiveValue;

	private ObscuredInt m_dbPassiveValue;

	private Rune m_runePd;

	private RunePassive m_runePassiveInfo;

	private bool m_isRestricted;

	private ReturnSoul m_returnSoulPd;

	public int passiveId
	{
		get
		{
			return 0;
		}
	}

	public int passiveValue
	{
		get
		{
			return 0;
		}
	}

	public int dbPassiveValue
	{
		get
		{
			return 0;
		}
	}

	public Rune runePd
	{
		get
		{
			return null;
		}
	}

	public RunePassive runePassiveInfo
	{
		get
		{
			return null;
		}
	}

	public int SlotNumBaseOne
	{
		get
		{
			return 0;
		}
	}

	public bool isRestricted
	{
		get
		{
			return false;
		}
	}

	public ReturnSoul returnSoulPd
	{
		get
		{
			return null;
		}
	}

	public int returnNeedStele
	{
		get
		{
			return 0;
		}
	}

	public int returnNeedTicket
	{
		get
		{
			return 0;
		}
	}

	public int returnSoulAmount
	{
		get
		{
			return 0;
		}
	}

	public int id
	{
		get
		{
			return 0;
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

	public int dbLevel
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

	public float levelPercentage
	{
		get
		{
			return 0f;
		}
	}

	public TroopStat troopStat
	{
		get
		{
			return default(TroopStat);
		}
	}

	public UserUnit.UnitRes unitRes
	{
		get
		{
			return null;
		}
	}

	public string nameWithPrefix
	{
		get
		{
			return null;
		}
	}

	public RuneItemSlot(int runeId, int runePassiveId, int exp, int idx)
	{
	}

	public RuneItemSlot(UserRuneItem userRuneItem, int exp, int idx)
	{
	}

	public void Update(int id, int passiveId, int exp, int idx)
	{
	}

	private void UpdateStat()
	{
	}

	public void Update(UserRuneItem userRuneItem, int exp, int idx)
	{
	}

	public int GetExpectedUnitRes(UserUnit userUnit, EnumData.UnitResType type)
	{
		return 0;
	}

	private int getRunePassiveValue(UserUnit userUnit, int passiveId)
	{
		return 0;
	}

	public bool CheckRuneSetCondition(int type, int mainStat, int tier)
	{
		return false;
	}

	public void UpdateFairRule(FairRule fairRule)
	{
	}
}
