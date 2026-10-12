using System.Collections.Generic;
using UnityEngine;

public class AutoEquipBestItem : MonoBehaviour
{
	public static ulong GetItem(ulong unitSrl, EnumData.ItemSlotType slotType)
	{
		return 0uL;
	}

	private static ulong GetWeapon(ulong unitSrl, List<ulong> items)
	{
		return 0uL;
	}

	private static ulong GetArmor(ulong unitSrl, List<ulong> items)
	{
		return 0uL;
	}

	private static ulong GetKit(ulong unitSrl, List<ulong> items)
	{
		return 0uL;
	}

	private static ulong GetItemMaxTEIP(ulong unitSrl, List<ulong> filteredItems, bool isOverZero)
	{
		return 0uL;
	}

	private static ulong GetItemMaxStatOver(ulong unitSrl, EnumData.StatType statType, List<ulong> selectedItemSrls)
	{
		return 0uL;
	}

	private static bool IsSameItem(ulong targetItemSrl, ulong selectedItemSrl)
	{
		return false;
	}

	private static bool IsSameItemID(int targetItemID, int selectedItemID)
	{
		return false;
	}

	private static int GetTEIP(ulong unitSrl, ulong selectedItemSrl)
	{
		return 0;
	}
}
