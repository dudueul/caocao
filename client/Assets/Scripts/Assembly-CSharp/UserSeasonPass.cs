using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserSeasonPass : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredString m_yyyymm;

	private ObscuredInt m_exp;

	private ObscuredInt m_normalLevel;

	private ObscuredInt m_eliteLevel;

	private ObscuredInt m_purchased;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong uid
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

	public int exp
	{
		get
		{
			return 0;
		}
	}

	public int normalLevel
	{
		get
		{
			return 0;
		}
	}

	public int eliteLevel
	{
		get
		{
			return 0;
		}
	}

	public bool purchased
	{
		get
		{
			return false;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
