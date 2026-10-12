using System;
using System.Collections.Generic;
using LitJson;

public class Basket : ITransmittable
{
	private Dictionary<ulong, BasketItem> m_items;

	private Action m_deserializeCallback;

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public EnumData.ShopMode shopMode { get; private set; }

	public BasketItem[] basketItems
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public BasketItem get_Item(ulong key)
	{
		return null;
	}

	// C# has no syntax for parameterized property 'Item'.
	public BasketItem get_Item(int key)
	{
		return null;
	}

	// C# has no syntax for parameterized property 'Item'.
	public BasketItem get_Item(ItemUI itemUI)
	{
		return null;
	}

	public Basket(EnumData.ShopMode shopMode)
	{
	}

	public bool ContainsKey(int key)
	{
		return false;
	}

	public bool Contains(ItemUI itemUI)
	{
		return false;
	}

	public BasketItem Add(BasketItem item)
	{
		return null;
	}

	public BasketItem Add(ItemUI itemUI)
	{
		return null;
	}

	public void Add(int key, BasketItem item)
	{
	}

	public bool Remove(int key)
	{
		return false;
	}

	public bool Remove(ItemUI itemUI)
	{
		return false;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	public int GetTotalPrice()
	{
		return 0;
	}

	public void Clear()
	{
	}
}
