using System;
using System.Collections.Generic;
using LitJson;

public class UserArenaRecords : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserArenaRecord> m_srl2ArenaRecord;

	private List<ulong> m_srls;

	public Action deserializeCallback;

	public Dictionary<ulong, UserArenaRecord> srl2Arena
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
	public UserArenaRecord get_Item(ulong srl)
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
