using System;
using System.Collections.Generic;
using LitJson;

public class GuildSiegeWarDefenceDecks : ITransmittable
{
	private Dictionary<int, GuildSiegeWarDefenceDeck> m_slot2Deck;

	private List<int> m_slots;

	public Dictionary<int, GuildSiegeWarDefenceDeck> slot2Deck
	{
		get
		{
			return null;
		}
	}

	public List<int> slots
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public GuildSiegeWarDefenceDeck get_Item(int slot)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

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

	public void DeserializeOne(JsonData jsonData)
	{
	}

	public bool IsDefenceGroup(int unitId, int controlSlot)
	{
		return false;
	}
}
