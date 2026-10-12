using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class WorldBossInstance : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredString m_yyyymmdd;

	private ObscuredInt m_phase;

	private ObscuredInt m_worldbossId;

	private ObscuredInt m_passive1;

	private ObscuredInt m_passive2;

	private ObscuredInt m_passive3;

	private ObscuredInt m_passive4;

	private ObscuredInt m_passive5;

	private ObscuredULong m_maxHp;

	private ObscuredULong m_curHp;

	private DateTime m_startDate;

	private DateTime m_endDate;

	private DateTime m_completeDate;

	private ObscuredInt m_isComplete;

	private DateTime m_earlyClearDate;

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

	public int passive1
	{
		get
		{
			return 0;
		}
	}

	public int passive2
	{
		get
		{
			return 0;
		}
	}

	public int passive3
	{
		get
		{
			return 0;
		}
	}

	public int passive4
	{
		get
		{
			return 0;
		}
	}

	public int passive5
	{
		get
		{
			return 0;
		}
	}

	public ulong maxHp
	{
		get
		{
			return 0uL;
		}
	}

	public ulong curHp
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

	public DateTime endDate
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

	public DateTime ealryClearDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public bool isComplete
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
