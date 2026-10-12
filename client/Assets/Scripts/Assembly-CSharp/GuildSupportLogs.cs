using System;
using System.Collections.Generic;
using LitJson;

public class GuildSupportLogs : ITransmittable
{
	private Dictionary<ulong, GuildSupportLog> m_srl2Log;

	private List<ulong> m_srls;

	public Dictionary<ulong, GuildSupportLog> srl2Log
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSupportLog get_Item(ulong srl)
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

	[DoNotJsonfy]
	public ulong firstSrl
	{
		get
		{
			return 0uL;
		}
	}

	[DoNotJsonfy]
	public ulong lastSrl
	{
		get
		{
			return 0uL;
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
