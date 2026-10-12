using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class CustomEventUser : ITransmittable
{
	public class EventResultData
	{
		public List<ulong> RewardSrl;

		public void Deserialize(JsonData rewardResult)
		{
		}
	}

	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredULong m_eventUnitId;

	private ObscuredInt m_eventUnitType;

	private ObscuredInt m_recordValue;

	private EventResultData m_rewardResult;

	private DateTime m_rewardDate;

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

	public ulong eventUnitId
	{
		get
		{
			return 0uL;
		}
	}

	public int eventUnitType
	{
		get
		{
			return 0;
		}
	}

	public int recordValue
	{
		get
		{
			return 0;
		}
	}

	public List<ulong> rewardSrl
	{
		get
		{
			return null;
		}
	}

	public DateTime rewardDate
	{
		get
		{
			return default(DateTime);
		}
	}

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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
