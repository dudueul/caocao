using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserUnitTree : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_uid;

	private ObscuredInt m_id;

	private UnitTreeStatus m_status;

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

	public int id
	{
		get
		{
			return 0;
		}
	}

	public UnitTreeStatus status
	{
		get
		{
			return UnitTreeStatus.none;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
