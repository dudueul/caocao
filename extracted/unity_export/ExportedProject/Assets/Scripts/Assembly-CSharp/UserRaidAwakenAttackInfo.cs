using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserRaidAwakenAttackInfo : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_guildId;

	private ObscuredULong m_userId;

	private ObscuredULong m_raidId;

	private ObscuredInt m_raidType;

	private ObscuredInt m_difficulty;

	private ObscuredInt m_maxHp;

	private ObscuredInt m_damage;

	private ObscuredInt m_damageAftKilled;

	private List<int> m_usedUnits;

	private ObscuredInt m_enterCount;

	private ObscuredInt m_enterRefreshCount;

	private string m_userName;

	private string m_userJob;

	private ObscuredInt m_userExp;

	private ObscuredInt m_userPower;

	private DateTime m_updDate;

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

	public ulong UserId
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

	public int MaxHp
	{
		get
		{
			return 0;
		}
	}

	public int Damage
	{
		get
		{
			return 0;
		}
	}

	public int DamageAftKilled
	{
		get
		{
			return 0;
		}
	}

	public List<int> Units
	{
		get
		{
			return null;
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

	public string UserName
	{
		get
		{
			return null;
		}
	}

	public string UserJob
	{
		get
		{
			return null;
		}
	}

	public int UserExp
	{
		get
		{
			return 0;
		}
	}

	public int UserPower
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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsUsedUnit(int unitId)
	{
		return false;
	}
}
