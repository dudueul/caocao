using System;
using System.Collections.Generic;
using LitJson;

public class GoodsShopItemInfo
{
	public enum SaleType
	{
		Unlimited = 0,
		Once = 1,
		Monthly = 2,
		Daily = 3,
		Weekly = 4
	}

	public enum SaleRibbon
	{
		None = 0,
		First = 1,
		Once = 2
	}

	private GoodsShop m_pdData;

	private static DateTime defaultDateTime;

	private static string ms_timeFormat;

	public GoodsShop pdData
	{
		get
		{
			return null;
		}
	}

	public int m_id { get; private set; }

	public string m_productId { get; private set; }

	public string m_productType { get; private set; }

	public string m_name { get; private set; }

	public string m_content { get; private set; }

	public EnumData.GoodsType m_rewardType { get; private set; }

	public string m_atlas { get; private set; }

	public string m_imageName { get; private set; }

	public string m_price { get; private set; }

	public int m_reward0 { get; private set; }

	public int m_order { get; private set; }

	public int m_type0 { get; private set; }

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

	public bool WillShow(List<GoodsShopItemInfo> cashShopInfos)
	{
		return false;
	}

	public TimeSpan GetLastTermtime()
	{
		return default(TimeSpan);
	}

	public static GoodsShopItemInfo Find(List<GoodsShopItemInfo> infos, int id)
	{
		return null;
	}

	public static GoodsShopItemInfo FindParent(List<GoodsShopItemInfo> infos, int nextId)
	{
		return null;
	}
}
