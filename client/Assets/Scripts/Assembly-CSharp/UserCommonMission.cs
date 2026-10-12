using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserCommonMission : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_uid;

	private ObscuredInt m_missionId;

	private ObscuredInt m_value;

	private DateTime m_reward_date;

	private DateTime m_upd_date;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong uid
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public int missionId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public DateTime reward_date
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public DateTime upd_date
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public int requestedValue { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsCompleted()
	{
		return false;
	}

	public bool AcceptedReward()
	{
		return false;
	}
}
