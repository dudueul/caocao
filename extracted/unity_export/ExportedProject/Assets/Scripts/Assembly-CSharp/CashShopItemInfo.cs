using System;
using System.Collections.Generic;
using LitJson;

public class CashShopItemInfo
{
	public enum ScopeType
	{
		Constant = 0,
		Package = 1,
		Placement = 2,
		SeasonTicket = 3,
		LobbyEvent = 4,
		Limit = 5,
		LinkedGoods = 6
	}

	public enum SaleType
	{
		Unlimited = 0,
		Once = 1,
		Monthly = 2,
		Daily = 3,
		Weekly = 4,
		OncePerUser = 5
	}

	public enum SaleRibbon
	{
		None = 0,
		First = 1,
		Once = 2,
		Special = 3,
		HalfSale = 4,
		OncePerDay = 5,
		New = 6,
		Popularity = 7,
		Recommend = 8,
		SeasonTicket = 9
	}

	public enum Currency
	{
		TWD = 0,
		CNY = 1,
		JPY = 2,
		KRW = 3,
		USD = 4,
		HKD = 5
	}

	private static DateTime defaultDateTime;

	private static string ms_timeFormat;

	public int m_id { get; private set; }

	public string m_productId { get; private set; }

	public string m_productType { get; private set; }

	public string m_name { get; private set; }

	public string m_content { get; private set; }

	public EnumData.GoodsType m_rewardType { get; private set; }

	public ScopeType m_scopeType { get; private set; }

	public string m_atlas { get; private set; }

	public string m_imageName { get; private set; }

	public string m_price { get; private set; }

	public int m_reward0 { get; private set; }

	public int m_order { get; private set; }

	public int m_type0 { get; private set; }

	public Currency m_currency { get; private set; }

	public string currencySymbol
	{
		get
		{
			return null;
		}
	}

	public bool m_monthly { get; private set; }

	public bool m_is1st { get; private set; }

	public bool m_onSale { get; private set; }

	public DateTime m_startTime { get; private set; }

	public DateTime m_endTime { get; private set; }

	public string m_tooltipDesc { get; private set; }

	public DateTime m_subsFinishTime { get; private set; }

	public int m_saleRibbon { get; private set; }

	public int m_nextId { get; private set; }

	public int m_saleType { get; private set; }

	public int m_saleCount { get; private set; }

	public int m_salePeriod { get; private set; }

	public int m_buyCount { get; private set; }

	public bool LimitedTimeOffer
	{
		get
		{
			return false;
		}
	}

	public override string ToString()
	{
		return null;
	}

	public void Deserialize(JsonData goodsData)
	{
	}

	public bool IsOnSalesPeriod()
	{
		return false;
	}

	public TimeSpan GetRemainTime()
	{
		return default(TimeSpan);
	}

	public TimeSpan GetNextSalesTime()
	{
		return default(TimeSpan);
	}

	public string GetStartTimeString()
	{
		return null;
	}

	public string GetEndTimeString()
	{
		return null;
	}

	public TimeSpan GetLastSubsActivatedTime()
	{
		return default(TimeSpan);
	}

	public bool IsOnSubsActivatedState()
	{
		return false;
	}

	public bool CanPurchase()
	{
		return false;
	}

	public bool WillShow(List<CashShopItemInfo> cashShopInfos)
	{
		return false;
	}

	public TimeSpan GetLastTermtime()
	{
		return default(TimeSpan);
	}

	public static CashShopItemInfo Find(List<CashShopItemInfo> infos, int id)
	{
		return null;
	}

	public static CashShopItemInfo FindParent(List<CashShopItemInfo> infos, int nextId)
	{
		return null;
	}

	public static string GetSaleRibbonToString(SaleRibbon ribbon)
	{
		return null;
	}
}
