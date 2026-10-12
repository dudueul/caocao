using System;
using System.Collections.Generic;
using LitJson;

public class UserSoldierDecks : ITransmittable
{
	private Dictionary<int, UserSoldierDeck> m_id2SoldierDecks;

	private List<int> m_deckIds;

	public Dictionary<int, UserSoldierDeck> soldierDecks
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserSoldierDeck get_Item(int deckId)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<int> deckIds
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void AddSoldierDeck(UserSoldierDeck soldierDeck)
	{
	}

	public UserSoldierDeck GetUserSoldierDeck(int deckId)
	{
		return null;
	}

	public bool CheckMultipleSoldierDeck()
	{
		return false;
	}

	public bool CheckDuplicateSoldierDeck(int deckId, int slotId = 0, int soldierId = 0)
	{
		return false;
	}

	private void AddCheckBound(int soldierId, ref Dictionary<int, int> boundary)
	{
	}

	private bool _CheckMultipleSoldierDeck(Dictionary<int, int> boundary, int soldierId)
	{
		return false;
	}
}
