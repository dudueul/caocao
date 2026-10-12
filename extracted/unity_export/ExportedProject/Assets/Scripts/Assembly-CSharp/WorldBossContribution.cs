using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class WorldBossContribution : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredString m_yyyymmdd;

	private ObscuredInt m_phase;

	private ObscuredInt m_worldbossId;

	private ObscuredULong m_maxPayment;

	private ObscuredULong m_curPayment;

	private DateTime m_startDate;

	private DateTime m_completeDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public string yyyymmdd
	{
		get
		{
			return null;
		}
	}

	public int phase
	{
		get
		{
			return 0;
		}
	}

	public int worldbossId
	{
		get
		{
			return 0;
		}
	}

	public ulong maxPayment
	{
		get
		{
			return 0uL;
		}
	}

	public ulong curPayment
	{
		get
		{
			return 0uL;
		}
	}

	public DateTime startDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime completeDate
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
