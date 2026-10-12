using System;
using CodeStage.AntiCheat.ObscuredTypes;

public class StoreItem
{
	private Item m_item;

	private UserItem m_userItem;

	public Item item
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public UserItem userItem
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public ObscuredString listSrl { get; private set; }

	public ObscuredInt srl { get; private set; }

	public EnumData.ShopMode shopMode { get; private set; }

	public ObscuredInt amount { get; protected set; }

	public ObscuredInt price { get; private set; }

	public EnumData.GoodsType costType { get; private set; }

	public EnumData.ItemType costItemType { get; private set; }

	public ObscuredInt pDataId { get; private set; }

	public ObscuredInt slot { get; private set; }

	public DateTime validDate { get; private set; }

	public DateTime lastUpdateTime { get; private set; }

	public TimeSpan remainTime
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public string remainTimeString
	{
		get
		{
			return null;
		}
	}

	public bool isTreasure
	{
		get
		{
			return false;
		}
	}

	public void SetStoreItemData(EnumData.ShopMode shopMode, Item item, string listSrl, int srl, int price, EnumData.GoodsType costType, int slot, int amount, DateTime validDate, DateTime lastUpdated)
	{
	}

	public void SetStoreItemData(EnumData.ShopMode shopMode, UserItem userItem)
	{
	}

	public void SetStoreItemData(EnumData.ShopMode shopMode, int dataId, Item item, int price, EnumData.ItemType costType, int slot, int amount)
	{
	}

	public void UpdateAmount(int amount)
	{
	}

	public bool IsVipLocked(out int requireLevel)
	{
		requireLevel = default(int);
		return false;
	}

	public void SetExpired()
	{
	}
}
