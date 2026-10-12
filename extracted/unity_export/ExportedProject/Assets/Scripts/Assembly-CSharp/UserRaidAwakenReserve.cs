using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserRaidAwakenReserve : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_guildId;

	private ObscuredInt m_idx;

	private ObscuredInt m_dayOfWeek;

	private ObscuredInt m_hour;

	private ObscuredInt m_raidType;

	private ObscuredInt m_difficulty;

	private ObscuredInt m_multiplier;

	private DateTime m_lastSummonDate;

	private ObscuredInt m_calibrate;

	private ObscuredInt m_deleted;

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

	public int Idx
	{
		get
		{
			return 0;
		}
	}

	public int DayOfWeek
	{
		get
		{
			return 0;
		}
	}

	public int Hour
	{
		get
		{
			return 0;
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

	public int Multiplier
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

	public bool Calibrate
	{
		get
		{
			return false;
		}
	}

	public bool Deleted
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
}
