public class RuneBoxItemInfo
{
	private const int m_BlueDragonSoulItemId = 3100042;

	private const int m_VermilionSoulItemId = 3100058;

	private const int m_WhiteTigerSoulItemId = 3100090;

	private const int m_BlackTortoiseSoulItemId = 3100092;

	public RuneBoxShop boxPd;

	public int id
	{
		get
		{
			return 0;
		}
	}

	public RuneBoxItemInfo(RuneBoxShop boxPd)
	{
	}

	public EnumData.GoodsType GetCostGoodsType()
	{
		return EnumData.GoodsType.None;
	}

	public int GetCostItemType()
	{
		return 0;
	}

	public string GetNeedItemSpriteNameOfIconAtlas()
	{
		return null;
	}

	public int calcTotalNeedItemCount(int count)
	{
		return 0;
	}
}
