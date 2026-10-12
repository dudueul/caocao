using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserUnitPassiveInfo : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_user_id;

	private ObscuredInt m_current_point;

	private ObscuredInt m_max_point;

	private ObscuredInt m_reset_count;

	private DateTime m_reset_date;

	private ObscuredInt m_refill_count;

	private DateTime m_refill_date;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong user_id
	{
		get
		{
			return 0uL;
		}
	}

	public int current_point
	{
		get
		{
			return 0;
		}
	}

	public int remainResetCount
	{
		get
		{
			return 0;
		}
	}

	public int refillCost
	{
		get
		{
			return 0;
		}
	}

	public int refillCount
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
