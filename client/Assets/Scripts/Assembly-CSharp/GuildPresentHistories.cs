using System;
using System.Collections.Generic;
using LitJson;

public class GuildPresentHistories : ITransmittable
{
	private Dictionary<int, GuildPresentHistory> m_srl2History;

	private List<int> m_srls;

	public Dictionary<int, GuildPresentHistory> srl2History
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildPresentHistory get_Item(int srl)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<int> srls
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
}
