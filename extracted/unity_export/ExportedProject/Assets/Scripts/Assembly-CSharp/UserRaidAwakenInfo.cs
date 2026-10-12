using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserRaidAwakenInfo : ITransmittable, IRaidInfo
{
	private ObscuredULong m_srl;

	private ObscuredULong m_guildId;

	private ObscuredInt m_raidType;

	private ObscuredInt m_difficulty;

	private ObscuredInt m_cleardBD;

	private ObscuredInt m_cleardVB;

	private ObscuredInt m_cleardWT;

	private ObscuredInt m_cleardBT;

	private ObscuredULong m_raidId;

	private ObscuredInt m_summonCount;

	private ObscuredInt m_summonRefreshCount;

	private DateTime m_lastSummonDate;

	private ObscuredInt m_rewardReceived;

	private ObscuredInt m_rage;

	private ObscuredInt m_hp;

	private ObscuredInt m_mp;

	private ObscuredInt m_maxHp;

	private ObscuredInt m_maxMp;

	private ObscuredInt m_multiplier;

	private Raid m_raid;

	private DateTime m_expireDate;

	private DateTime m_minActiveDate;

	public ulong Srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong GuildId
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

	public int CleardBD
	{
		get
		{
			return 0;
		}
	}

	public int CleardVB
	{
		get
		{
			return 0;
		}
	}

	public int CleardWT
	{
		get
		{
			return 0;
		}
	}

	public int CleardBT
	{
		get
		{
			return 0;
		}
	}

	public ulong RaidId
	{
		get
		{
			return 0uL;
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

	public int RewardReceived
	{
		get
		{
			return 0;
		}
	}

	public int Rage
	{
		get
		{
			return 0;
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

	public DateTime ExpireDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime MinActiveDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int Multiplier
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

	public bool IsEalryClear()
	{
		return false;
	}

	public bool IsActive()
	{
		return false;
	}

	public bool Cleard()
	{
		return false;
	}

	public bool IsEnableSummon()
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

	public int GetHighestDifficulty(EnumData.RaidType raidType)
	{
		return 0;
	}
}
