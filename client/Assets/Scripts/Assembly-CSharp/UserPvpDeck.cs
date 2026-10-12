using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserPvpDeck : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_formationSrl;

	private ObscuredULong m_groupSrl;

	private ObscuredInt m_warId;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong formationSrl
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong groupSrl
	{
		get
		{
			return 0uL;
		}
	}

	public int warId
	{
		get
		{
			return 0;
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
