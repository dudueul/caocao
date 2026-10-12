using System.Collections.Generic;
using LitJson;

public class ArenaOwnData : NPCData
{
	public const int ARENA_UID = 5;

	private List<UserUnit> lstUserUnit;

	public static ArenaOwnData instance
	{
		get
		{
			return null;
		}
	}

	public static bool hasInstance
	{
		get
		{
			return false;
		}
	}

	public void RemoveUserItemTreasure(ulong srl)
	{
	}

	public ulong UpsertUserItemTreasure(int itemId)
	{
		return 0uL;
	}

	public ulong UpsertUserUnit(UserArenaUnit arenaUnit)
	{
		return 0uL;
	}

	public List<UserUnit> GetUserUnits()
	{
		return null;
	}

	public override UserUnit GetUnitEquipped(ulong itemSrl)
	{
		return null;
	}

	public override ulong GetUnitEquippedSrl(ulong itemSrl)
	{
		return 0uL;
	}

	public override List<ulong> GetInventoryItems(EnumData.ItemSlotType slotType, UserUnit userUnit, bool exceptEquipped = true)
	{
		return null;
	}

	public void Serialize(JsonWriter writer)
	{
	}

	private void SerializeArenaUnits(JsonWriter writer)
	{
	}
}
