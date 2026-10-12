using System;
using System.Collections.Generic;
using LitJson;

public class UserArenaShopItems : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserArenaShopItem> m_idx2Item;

	private List<int> m_indices;

	public Action deserializeCallback;

	public Dictionary<int, UserArenaShopItem> idx2Item
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<int> indices
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserArenaShopItem get_Item(int idx)
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
