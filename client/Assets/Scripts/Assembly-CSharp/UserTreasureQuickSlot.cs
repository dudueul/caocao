using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class UserTreasureQuickSlot : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_uid;

	private ObscuredInt m_jobType;

	private ObscuredInt m_deckId;

	private ObscuredString m_name;

	private ObscuredULong m_weapon;

	private ObscuredULong m_armor;

	private ObscuredULong m_kit;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong uid
	{
		get
		{
			return 0uL;
		}
	}

	public int jobType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int deckId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public ulong weapon
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong armor
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong kit
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public ulong GetEquippedItemSrl(EnumData.ItemSlotType itemSlotType)
	{
		return 0uL;
	}

	public bool IsEquippedItem(ulong itemSrl)
	{
		return false;
	}

	public bool IsEquippedItem(EnumData.ItemSlotType slotType, ulong itemSrl)
	{
		return false;
	}

	public void EquipToUnit(MonoBehaviour caller, UserUnit userUnit, Action callback)
	{
	}
}
