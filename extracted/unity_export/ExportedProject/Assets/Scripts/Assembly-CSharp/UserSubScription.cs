using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserSubScription : ITransmittable
{
	private ObscuredInt m_cashShopPdId;

	private DateTime m_finishTime;

	public int cashShopId
	{
		get
		{
			return 0;
		}
	}

	public DateTime finishTime
	{
		get
		{
			return default(DateTime);
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
