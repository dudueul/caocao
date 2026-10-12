using System;
using System.Collections.Generic;
using LitJson;

public class GuildHistories : ITransmittable
{
	private Dictionary<ulong, GuildHistory> m_srl2History;

	private List<ulong> m_srls;

	public Dictionary<string, Dictionary<ulong, GuildHistory>> date2history;

	public Dictionary<ulong, GuildHistory> srl2History
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildHistory get_Item(ulong srl)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<ulong> srls
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public int Count
	{
		get
		{
			return 0;
		}
	}

	public Action<int> deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Deserialize(JsonData jsonData, int lastSrl)
	{
	}

	public void Clear()
	{
	}

	public string GetLeaverNationName(ulong leaverId)
	{
		return null;
	}
}
