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
			return m_srl2Item;
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
		// [RE] server sends an array of inventory rows; upsert each into the
		// srl/id indexes (original accepted {srl,id,exp,amount,...}).
		if (jsonData == null) return;
		if (jsonData.IsArray)
		{
			for (int i = 0; i < jsonData.Count; i++)
				UpsertFromRow(jsonData[i]);
			return;
		}
		UpsertFromRow(jsonData);
	}

	// [RE] rebuild-only upsert — the original GetAddedItems/Deserialize path
	// did the same via the srl-based rows.
	public UserItem UpsertFromRow(JsonData row)
	{
		if (row == null) return null;
		var item = new UserItem();
		if (row.Keys.Contains("srl"))
			item.srl = (ulong)row["srl"];
		else
			item.srl = ++ms_srl;
		item.Deserialize(row);
		m_srl2Item[item.srl] = item;
		m_id2Items[item.id] = item;
		m_srls.Add(item.srl);
		item.isAdded = true;
		return item;
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
		return GetItemCount(itemId) > 0;
	}

	public int GetItemCount(int itemId)
	{
		int sum = 0;
		foreach (var kv in m_srl2Item)
			if (kv.Value.id == itemId)
				sum += kv.Value.count;
		return sum;
	}

	public int GetItemCountNotEquipped(int itemId)
	{
		return 0;
	}

	public UserItem GetItem(int itemId)
	{
		foreach (var kv in m_srl2Item)
			if (kv.Value.id == itemId)
				return kv.Value;
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
