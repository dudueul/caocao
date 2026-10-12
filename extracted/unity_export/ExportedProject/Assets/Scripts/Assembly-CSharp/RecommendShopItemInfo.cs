using LitJson;

public class RecommendShopItemInfo
{
	public enum ShopType
	{
		None = 0,
		CashShop = 1,
		GoodsShop = 2
	}

	private RecommendShop m_pdData;

	public RecommendShop pdData
	{
		get
		{
			return null;
		}
	}

	public int m_id { get; private set; }

	public int m_productId { get; private set; }

	public ShopType m_shopType { get; private set; }

	public int m_order { get; private set; }

	public CashShopItemInfo.SaleRibbon m_saleRibbon { get; private set; }

	public void Deserialize(JsonData goodsData)
	{
	}
}
