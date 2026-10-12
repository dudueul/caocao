using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserLinkedGoodsNode : ITransmittable
{
	protected ObscuredULong m_srl;

	protected ObscuredULong m_linkedGoodsSrl;

	protected ObscuredInt m_linkedGoodsPdId;

	protected ObscuredULong m_uid;

	protected ObscuredInt m_pdId;

	protected ObscuredInt m_purchaseCount;

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

	public ulong linkedGoodsSrl
	{
		get
		{
			return 0uL;
		}
	}

	public int linkedGoodsPdId
	{
		get
		{
			return 0;
		}
	}

	public int pdId
	{
		get
		{
			return 0;
		}
	}

	public int purchaseCount
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
