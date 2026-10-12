using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserLinkedGoods : ITransmittable
{
	protected ObscuredULong m_srl;

	protected ObscuredULong m_uid;

	protected ObscuredInt m_id;

	protected ObscuredInt m_resetCount;

	public int m_pdId
	{
		get
		{
			return 0;
		}
	}

	public LinkedGoods m_pdData
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public int maxCycleCount
	{
		get
		{
			return 0;
		}
	}

	public int resetCount
	{
		get
		{
			return 0;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool CycleRepeatable()
	{
		return false;
	}

	public bool CanResetCycle()
	{
		return false;
	}
}
