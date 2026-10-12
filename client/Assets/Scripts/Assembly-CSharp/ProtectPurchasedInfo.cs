using System;
using System.Collections.Generic;
using LitJson;

public class ProtectPurchasedInfo : ITransmittable
{
	private Dictionary<int, DateTime> id2PurchasedTime;

	// C# has no syntax for parameterized property 'Item'.
	public DateTime get_Item(int id)
	{
		return default(DateTime);
	}

	protected override void DeserializeImpl(JsonData data)
	{
	}

	public void UpdatePurchasedTime(int id, DateTime purchasedTime)
	{
	}
}
