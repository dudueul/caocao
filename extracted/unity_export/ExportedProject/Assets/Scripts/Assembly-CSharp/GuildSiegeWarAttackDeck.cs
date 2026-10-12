using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildSiegeWarAttackDeck : ITransmittable
{
	public enum UsedType
	{
		Loaded = 0,
		UsedToFacility = 1,
		UsedToFortress = 2,
		Support = 3,
		Supported = 4
	}

	public class UnitInfo
	{
		public int unitId;

		public UsedType usedType;
	}

	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredULong m_guildId;

	private JsonData m_unitList;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong userId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public List<UnitInfo> unitInfos { get; protected set; }

	public DateTime updDate
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

	public bool expired
	{
		get
		{
			return false;
		}
	}

	private bool needReset
	{
		get
		{
			return false;
		}
	}

	public bool isNeedSetting
	{
		get
		{
			return false;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool HasUnit(int unitId)
	{
		return false;
	}

	public List<UserUnit> GetUserUnits()
	{
		return null;
	}
}
