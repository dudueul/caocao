using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class CustomEventReward : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_eventUnitId;

	private ObscuredInt m_orderId;

	private ObscuredInt m_conditionType;

	private ObscuredString m_description;

	private ObscuredLong m_goalMin;

	private ObscuredLong m_goalMax;

	private List<RewardInfo> m_rewards;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public ulong srl
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

	public int orderId
	{
		get
		{
			return 0;
		}
	}

	public int conditionType
	{
		get
		{
			return 0;
		}
	}

	public string description
	{
		get
		{
			return null;
		}
	}

	public long goalMin
	{
		get
		{
			return 0L;
		}
	}

	public long goalMax
	{
		get
		{
			return 0L;
		}
	}

	public List<RewardInfo> rewards
	{
		get
		{
			return null;
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

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
