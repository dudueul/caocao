using System;
using UnityEngine;

[ExecuteInEditMode]
public class EquipUI : MonoBehaviour
{
	public PrefabInstance m_weaponInst;

	public PrefabInstance m_armorInst;

	public PrefabInstance m_kitInst;

	private ItemUI m_weaponUI;

	private ItemUI m_armorUI;

	private ItemUI m_kitUI;

	private bool m_isTresureEquip;

	public UserUnit userUnit { get; private set; }

	public UserTreasureQuickSlot quickSlot { get; private set; }

	public UserItem weapon { get; private set; }

	public UserItem armor { get; private set; }

	public UserItem kit { get; private set; }

	public bool weaponAvailable { get; private set; }

	public bool armorAvailable { get; private set; }

	public bool kitAvailable { get; private set; }

	public ItemUI weaponUI
	{
		get
		{
			return null;
		}
	}

	public ItemUI armorUI
	{
		get
		{
			return null;
		}
	}

	public ItemUI kitUI
	{
		get
		{
			return null;
		}
	}

	public Action<ItemUI> OnClickHandler
	{
		set
		{
		}
	}

	public Action<ItemUI> OnDoubleClickHandler
	{
		set
		{
		}
	}

	public Action<ItemUI> OnSelectHandler
	{
		set
		{
		}
	}

	public Action<ItemUI> OnPressHandler
	{
		set
		{
		}
	}

	public Action<ItemUI> OnReleaseHandler
	{
		set
		{
		}
	}

	public Action<ItemUI> OnClickNoCheckValidHandler
	{
		set
		{
		}
	}

	public Action OnUpdatedItemHandler
	{
		set
		{
		}
	}

	private void OnDestroy()
	{
	}

	public void SetUserUnitForTresureEquip(UserUnit userUnit)
	{
	}

	public void SetUserUnit(UserUnit userUnit)
	{
	}

	public void SetUserQuickSlotForTreasureEquip(UserUnit userUnit, UserTreasureQuickSlot quickSlot)
	{
	}

	private void SetUserQuickSlot(UserUnit userUnit, UserTreasureQuickSlot quickSlot)
	{
	}

	public void SetSoldierEquips(UserUnit soldierUnit, UserItem weaponItem, UserItem armorItem, UserItem kinItem)
	{
	}

	private void UpdateEquipItems(bool userUnitChanged)
	{
	}

	public ItemUI GetItemUIByType(EnumData.ItemSlotType slotType)
	{
		return null;
	}

	private void SetTreasureEquip(UserUnit userUnit)
	{
	}

	private void SetDefaultTreasureEquip()
	{
	}
}
