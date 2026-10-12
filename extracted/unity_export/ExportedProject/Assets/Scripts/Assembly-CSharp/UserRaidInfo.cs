using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserRaidInfo : ITransmittable, IRaidInfo
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredULong m_raidId;

	private ObscuredInt m_raidType;

	private ObscuredInt m_curDifficulty;

	private ObscuredInt m_highestDifficulty;

	private ObscuredInt m_enterCount;

	private ObscuredInt m_enterRefreshCount;

	private ObscuredInt m_summonCount;

	private ObscuredInt m_summonRefreshCount;

	private DateTime m_lastSummonDate;

	private List<int> m_units;

	private ObscuredInt m_hp;

	private ObscuredInt m_mp;

	private ObscuredInt m_maxHp;

	private ObscuredInt m_maxMp;

	private DateTime m_updDate;

	private Raid m_raid;

	private DateTime m_expireDate;

	public ulong Srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong RaidId
	{
		get
		{
			return 0uL;
		}
	}

	public int RaidType
	{
		get
		{
			return 0;
		}
	}

	public int Difficulty
	{
		get
		{
			return 0;
		}
	}

	public int HighestDifficulty
	{
		get
		{
			return 0;
		}
	}

	public int EnterCount
	{
		get
		{
			return 0;
		}
	}

	public int EnterRefreshCount
	{
		get
		{
			return 0;
		}
	}

	public int SummonCount
	{
		get
		{
			return 0;
		}
	}

	public int SummonRefreshCount
	{
		get
		{
			return 0;
		}
	}

	public DateTime LastSummonDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public List<int> Units
	{
		get
		{
			return null;
		}
	}

	public int Hp
	{
		get
		{
			return 0;
		}
	}

	public int Mp
	{
		get
		{
			return 0;
		}
	}

	public int MaxHp
	{
		get
		{
			return 0;
		}
	}

	public int MaxMp
	{
		get
		{
			return 0;
		}
	}

	public DateTime UpdateDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime ExpireDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public ulong GuildId
	{
		get
		{
			return 0uL;
		}
	}

	public int Rage
	{
		get
		{
			return 0;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsActive()
	{
		return false;
	}

	public bool Cleard()
	{
		return false;
	}

	public bool IsUsedUnit(int unitId)
	{
		return false;
	}

	public bool IsEnableRefreshSummon()
	{
		return false;
	}

	public int GetMaxRes(EnumData.UnitResType resType)
	{
		return 0;
	}

	public int GetRes(EnumData.UnitResType resType)
	{
		return 0;
	}

	public int GetGoalValue()
	{
		return 0;
	}

	public int GetBattleStartRes(EnumData.UnitResType resType)
	{
		return 0;
	}
}
