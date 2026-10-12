public class ExchangeStoreItem : StoreItem
{
	public enum RewardType
	{
		Item = 0,
		Goods = 1
	}

	public Item costItem { get; private set; }

	public EnumData.GoodsType rewardGoodsType { get; private set; }

	public int rewardGoodsAmount { get; private set; }

	public int rewardItemAmount { get; private set; }

	public RewardType rewardType { get; private set; }

	public string icon { get; private set; }

	public int remainCount { get; private set; }

	public string ItemName
	{
		get
		{
			return null;
		}
	}

	public string ItemType
	{
		get
		{
			return null;
		}
	}

	public bool SoldOut
	{
		get
		{
			return false;
		}
	}

	public string SoldOutText
	{
		get
		{
			return null;
		}
	}

	public int OwnedAmount
	{
		get
		{
			return 0;
		}
	}

	public void SetStoreItemData(int shopId, int slotIndex, Item rewardItem, int rewardAmount, Item costItem, int cost, string costIcon, int remainCount)
	{
	}

	public void SetStoreItemData(int shopId, int slotIndex, EnumData.GoodsType rewardType, int rewardAmount, Item costItem, int cost, string costIcon, int remainCount)
	{
	}

	private int GetItemIdByGoodsType(EnumData.GoodsType goodsType)
	{
		return 0;
	}
}
