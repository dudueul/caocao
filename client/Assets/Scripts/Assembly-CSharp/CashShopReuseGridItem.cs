public class CashShopReuseGridItem : UIReuseScrollViewCell
{
	public class CashShopCellData : IReuseCellData
	{
		public int Index { get; set; }

		public int width { get; set; }

		public int height { get; set; }

		public PanelPopupBaseShop.TabMenu tabMenu { get; set; }

		public CashShopItemInfo cashShopItemInfo { get; set; }

		public GoodsShopItemInfo goodsShopItemInfo { get; set; }

		public TreasureBoxItemInfo treasureBoxItemInfo { get; set; }

		public CashShopProtectInfo cashShopProtectInfo { get; set; }

		public RecommendShopItemInfo recommendShopItemInfo { get; set; }

		public RuneBoxItemInfo runeBoxItemInfo { get; set; }
	}

	private CashShopCellData m_cashShopCellData;

	public CashShopCellData cashShopCellData
	{
		get
		{
			return null;
		}
	}

	public PanelPopupBaseShop.TabMenu tabMenu { get; set; }

	public CashShopItemInfo cashShopItemInfo { get; set; }

	public CashShopGridItem cashShopGridItem { get; set; }

	public GoodsShopItemInfo goodsShopItemInfo { get; set; }

	public CashShopProtectInfo cashShopProtectInfo { get; set; }

	public TreasureBoxItemInfo treasureBoxItemInfo { get; set; }

	public RecommendShopItemInfo recommendShopItemInfo { get; set; }

	public RuneBoxItemInfo runeBoxItemInfo { get; set; }

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}
}
