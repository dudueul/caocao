using System;
using System.Collections.Generic;
using LitJson;

public class UserSeasonPasses : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserSeasonPass> m_srl2SeasonPass;

	private List<ulong> m_srls;

	private Dictionary<string, UserSeasonPass> m_yyyymm2SeasonPass;

	public Action deserializeCashShopInfoCallback;

	public Action deserializeCallback;

	public Dictionary<ulong, UserSeasonPass> srl2SeasonPass
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	public CashShopItemInfo cashshopItemInfo { get; private set; }

	public CashShopItemInfo discountedCashshopItemInfo { get; private set; }

	public bool cashshopItemPurchased { get; private set; }

	public int purchaseLastDay { get; private set; }

	// C# has no syntax for parameterized property 'Item'.
	public UserSeasonPass get_Item(ulong srl)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeCashshopInfo(JsonData packet)
	{
	}

	public UserSeasonPass GetCurrentSeasonPass()
	{
		return null;
	}

	public string GetCurrentYYYYMM()
	{
		return null;
	}

	public int GetCurrentLevel()
	{
		return 0;
	}

	public SeasonPass GetSeasonPassPd(string yyyymm)
	{
		return null;
	}
}
