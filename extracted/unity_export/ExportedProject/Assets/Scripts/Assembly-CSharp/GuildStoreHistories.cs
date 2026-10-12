using System;
using System.Collections.Generic;
using LitJson;

public class GuildStoreHistories : ITransmittable
{
	private Dictionary<int, GuildStoreHistory> m_shopId2History;

	private List<int> m_shopIds;

	public Dictionary<int, GuildStoreHistory> shopId2History
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildStoreHistory get_Item(int shopId)
	{
		return null;
	}

	public List<int> shopIds
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

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}
}
