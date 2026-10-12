using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildWarScheduleDetailGroup : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_seasonKey;

	private ObscuredInt m_groupIndex;

	private ObscuredInt m_seasonDetailKey;

	private DateTime m_closingDate;

	private DateTime m_endingDate;

	private ObscuredInt m_status;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public int seasonKey
	{
		get
		{
			return 0;
		}
	}

	public int groupIndex
	{
		get
		{
			return 0;
		}
	}

	public int seasonDetailKey
	{
		get
		{
			return 0;
		}
	}

	public DateTime closingDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime endingDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public int status
	{
		get
		{
			return 0;
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

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
