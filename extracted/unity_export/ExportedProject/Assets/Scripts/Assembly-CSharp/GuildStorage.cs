using System;
using System.Collections.Generic;
using LitJson;

public class GuildStorage : ITransmittable
{
	public Dictionary<int, GuildItem> m_id2Item;

	private List<int> m_ids;

	public Action deserializeCallback;

	public Action deserializePresentHistoryCallback;

	public Dictionary<int, GuildItem> id2Item
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildItem get_Item(int itemId)
	{
		return null;
	}

	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializePresentHistories(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}
}
