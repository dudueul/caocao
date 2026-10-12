using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class DailyGiftHistory : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_dailyGiftId;

	private ObscuredInt m_state;

	private DateTime m_regDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public int dailyGiftId
	{
		get
		{
			return 0;
		}
	}

	public int state
	{
		get
		{
			return 0;
		}
	}

	public DateTime regDate
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
}
