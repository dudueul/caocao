using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserSoldier : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_uid;

	private ObscuredInt m_soldierId;

	private ObscuredInt m_level;

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

	public int soldierId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int soldierLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public Action deserializeCallback { get; set; }

	public UserSoldier()
	{
	}

	public UserSoldier(ulong uid, ulong srl, int soldierId, int level)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
