using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class CustomEventUnit : ITransmittable
{
	private ObscuredULong m_id;

	private ObscuredULong m_eventId;

	private ObscuredInt m_orderId;

	private ObscuredInt m_typeId;

	private ObscuredInt m_goal;

	private ObscuredString m_description;

	private ObscuredString m_description2;

	private ObscuredInt m_checkVal1;

	private ObscuredInt m_checkVal2;

	private ObscuredInt m_contributionPoint;

	private List<RewardInfo> m_rewards;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public ulong id
	{
		get
		{
			return 0uL;
		}
	}

	public ulong eventId
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

	public int typeId
	{
		get
		{
			return 0;
		}
	}

	public int goal
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

	public string description2
	{
		get
		{
			return null;
		}
	}

	public int checkVal1
	{
		get
		{
			return 0;
		}
	}

	public int checkVal2
	{
		get
		{
			return 0;
		}
	}

	public int contributionPoint
	{
		get
		{
			return 0;
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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
