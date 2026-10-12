using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class CustomEventRewardLog : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredLong m_eventRewardSrl;

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

	public long eventRewardSrl
	{
		get
		{
			return 0L;
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
