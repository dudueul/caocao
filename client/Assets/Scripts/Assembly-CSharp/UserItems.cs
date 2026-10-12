using System;
using System.Collections.Generic;
using LitJson;
using Thingsoft.Pool;

public class UserItems : ITransmittable, iTransmitContainer, IUpsertedMarkable
{
	public static ulong ms_srl;

	private Dictionary<ulong, UserItem> m_srl2Item;

	private Dictionary<int, UserItem> m_id2Items;

	private List<ulong> m_srls;

	public bool trackGetItem;

	private static string checkIgnoreKey;

	private static bool[] _inventoryItemTypeMarker;

	public Dictionary<ulong, UserItem> srl2Item
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

	// C# has no syntax for parameterized property 'Item'.
	public UserItem get_Item(ulong srl)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

	[DoNotJsonfy]
	public bool isAdded
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	[DoNotJsonfy]
	public bool isUpdated
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private bool[] inventoryItemTypeMarker
	{
		get
		{
			return null;
		}
	}

	public bool isAddedOnventory
	{
		get
		{
			return false;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeEnhances(JsonData jsonData)
	{
	}

	private bool IsIgnoreItem(JsonData jsonData)
	{
		return false;
	}

	public bool HasConsumable(int itemId)
	{
		return false;
	}

	public ErrorCode.UserItem ConsumeItem(int itemId, out ulong srl)
	{
		srl = default(ulong);
		return ErrorCode.UserItem.SUCCEED;
	}

	public void RevertConsumeItem(ulong srl)
	{
	}

	public void GetConsumables(Thingsoft.Pool.UserItems res)
	{
	}

	public bool HasConsumables()
	{
		return false;
	}

	public bool HasItemByItemId(int itemId)
	{
		return false;
	}

	public int GetItemCount(int itemId)
	{
		return 0;
	}

	public int GetItemCountNotEquipped(int itemId)
	{
		return 0;
	}

	public UserItem GetItem(int itemId)
	{
		return null;
	}

	public UserItem GetItemNotEquipped(int itemId)
	{
		return null;
	}

	public List<UserItem> GetItemWithType(EnumData.ItemType itemType, List<UserItem> storage = null)
	{
		return null;
	}

	public List<UserItem> GetItemWithTreasureType(EnumData.TreasureType treasureType, int tier, List<UserItem> storage = null)
	{
		return null;
	}

	public List<UserItem> GetItemWithTreasureTypeAndEnhanceLevel(EnumData.TreasureType treasureType, int tier, int enhanceLevel)
	{
		return null;
	}

	public bool HasItemWithItemTypeAndPassiveId(int itemType, int passive)
	{
		return false;
	}

	public List<UserItem> GetItemWithItemTypeAndPassiveId(int itemType, int passive)
	{
		return null;
	}

	public bool isEquip(ulong itemSrl)
	{
		return false;
	}

	public List<ulong> GetNotEquippedEquipmentSrls(int itemId)
	{
		return null;
	}

	private List<UserItem> GetAddedItems(JsonData units)
	{
		return null;
	}

	public List<UserItem> GetAddedItemsOnInventory()
	{
		return null;
	}
}
