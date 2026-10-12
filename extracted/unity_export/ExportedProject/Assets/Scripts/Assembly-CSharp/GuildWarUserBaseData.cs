using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildWarUserBaseData : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_gold;

	private ObscuredInt m_base_atk;

	private ObscuredInt m_base_def;

	private ObscuredInt m_base_inte;

	private ObscuredInt m_base_agi;

	private ObscuredInt m_base_mor;

	private ObscuredInt m_familiar_point;

	private ObscuredInt m_passive_point;

	private ObscuredInt m_guildwar_point;

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

	public int gold
	{
		get
		{
			return 0;
		}
	}

	public int base_atk
	{
		get
		{
			return 0;
		}
	}

	public int base_def
	{
		get
		{
			return 0;
		}
	}

	public int base_inte
	{
		get
		{
			return 0;
		}
	}

	public int base_agi
	{
		get
		{
			return 0;
		}
	}

	public int base_mor
	{
		get
		{
			return 0;
		}
	}

	public int familiar_point
	{
		get
		{
			return 0;
		}
	}

	public int passive_point
	{
		get
		{
			return 0;
		}
	}

	public int guildwar_point
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
