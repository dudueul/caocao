using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserRuneSetHistory : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_id;

	private Action deserializeCallback__BackingField;

	public int id
	{
		get
		{
			return 0;
		}
	}

	public event Action deserializeCallback
	{
		add
		{
		}
		remove
		{
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
