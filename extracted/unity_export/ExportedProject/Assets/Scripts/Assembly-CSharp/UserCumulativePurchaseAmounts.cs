using System;
using System.Collections.Generic;
using LitJson;

public class UserCumulativePurchaseAmounts : ITransmittable
{
	private Dictionary<ulong, UserCumulativePurchaseAmount> m_srl2Amounts;

	private List<ulong> m_srls;

	public Action deserializeCallback;

	public Dictionary<ulong, UserCumulativePurchaseAmount> srl2Amounts
	{
		get
		{
			return null;
		}
	}

	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserCumulativePurchaseAmount GetTargetCPA(string yyyymm)
	{
		return null;
	}

	public UserCumulativePurchaseAmount GetTargetCPA(DateTime time)
	{
		return null;
	}

	public UserCumulativePurchaseAmount GetCurrentCPA()
	{
		return null;
	}
}
