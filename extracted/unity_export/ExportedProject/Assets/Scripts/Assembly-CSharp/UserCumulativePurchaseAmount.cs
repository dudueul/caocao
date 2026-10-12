using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserCumulativePurchaseAmount : ITransmittable
{
	[StructLayout((LayoutKind)0, Size = 12)]
	private struct PurchaseReward
	{
		public int index;

		public int itemId;

		public int price;
	}

	private ObscuredULong m_srl;

	private ObscuredString m_yyyymm;

	private ObscuredInt m_purchase_amount;

	private ObscuredString m_currency_code;

	private JsonData m_rewards;

	private List<PurchaseReward> rewards;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public string yyyymm
	{
		get
		{
			return null;
		}
	}

	public int purchaseAmount
	{
		get
		{
			return 0;
		}
	}

	public string currencyCode
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public UserCumulativePurchaseAmount()
	{
	}

	public UserCumulativePurchaseAmount(string yyyymm)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool PurchaseRewardReceived(int index)
	{
		return false;
	}
}
