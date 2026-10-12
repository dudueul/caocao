using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserBattleResult
{
	private List<ObscuredULong> m_items;

	private List<ObscuredInt> m_saveFlags;

	private List<ObscuredInt> m_relations;

	public void RegisterItem(ulong srl)
	{
	}

	public void RegisterSaveFlag(int id)
	{
	}

	public void RegisterRelations(int id)
	{
	}

	public void Clear()
	{
	}

	public void Serialize(JsonWriter writer)
	{
	}

	private void SerializeUnits(JsonWriter writer)
	{
	}

	private void SerializeItems(JsonWriter writer)
	{
	}

	private void SerializeSaveFlags(JsonWriter writer)
	{
	}

	private void SerializeTurnInfos(JsonWriter writer)
	{
	}

	private void SerializeReleations(JsonWriter writer)
	{
	}

	private void SerializeHighestDamage(JsonWriter writer)
	{
	}
}
