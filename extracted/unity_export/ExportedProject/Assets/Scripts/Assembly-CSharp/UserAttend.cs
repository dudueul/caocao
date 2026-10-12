using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserAttend : ITransmittable
{
	private static PermanentData ms_pd;

	private ObscuredULong m_srl;

	private ObscuredString m_yyyymm;

	private ObscuredInt m_seq;

	private DateTime m_regdate;

	public Action deserializeCallback { get; set; }

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public string yyyymm
	{
		get
		{
			return null;
		}
	}

	public int seq
	{
		get
		{
			return 0;
		}
	}

	public DateTime regdate
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
