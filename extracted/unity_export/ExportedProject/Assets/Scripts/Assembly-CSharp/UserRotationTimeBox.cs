using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserRotationTimeBox : ITransmittable
{
	private ObscuredULong srl;

	private ObscuredULong userId;

	private ObscuredInt nextOrderId;

	private DateTime regDate;

	private DateTime updDate;

	public int boxOrderId
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
}
