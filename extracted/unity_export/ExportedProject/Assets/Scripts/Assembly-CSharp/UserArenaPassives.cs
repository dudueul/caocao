using System;
using System.Collections.Generic;
using LitJson;

public class UserArenaPassives : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserArenaPassive> m_srl2ArenaPassive;

	private List<ulong> m_srls;

	public Action deserializeCallback;

	public Dictionary<ulong, UserArenaPassive> srl2ArenaPassive
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
	public UserArenaPassive get_Item(ulong srl)
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
