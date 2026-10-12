using System;
using System.Collections.Generic;
using LitJson;

public class UserPvpDecks : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserPvpDeck> m_srl2PvpDecks;

	private List<ulong> m_srls;

	public Dictionary<ulong, UserPvpDeck> srl2Item
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
	public UserPvpDeck get_Item(ulong srl)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserPvpDeck GetUserPvpDeckByWarId(int warId)
	{
		return null;
	}
}
