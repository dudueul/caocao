using System.Collections.Generic;
using LitJson;

public class UserDefenseDecks : ITransmittable, iTransmitContainer
{
	private Dictionary<int, DefenseDeckUnit> m_unitId2DefenseDeck;

	private List<int> m_unitIds;

	[DoNotJsonfy]
	public List<int> unitIds
	{
		get
		{
			return null;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void AddUnit(DefenseDeckUnit deckUnit)
	{
	}

	public DefenseDeckUnit GetUnit(int unitId)
	{
		return null;
	}

	public bool IsDefenseUnit(int unitId)
	{
		return false;
	}

	public bool HasInjuredUnits(UserUnits userUnits, int townId)
	{
		return false;
	}

	public void SerializeTownDefenseDeck(JsonWriter writer, Dictionary<int, UnitPickInfo> deckInfo, ulong userId, int townId)
	{
	}

	public int GetNumUnitsIn(int townId)
	{
		return 0;
	}

	public List<DefenseDeckUnit> GetUnitsIn(int townId)
	{
		return null;
	}

	public void RemoveDeckWithTownID(int townId)
	{
	}
}
