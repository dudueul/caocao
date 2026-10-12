using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SoldierSlots : PermanentBase
{
	public List<SoldierSlot> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SoldierSlot> m_soldierSlots;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SoldierSlot> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SoldierSlot get_Item(ObscuredInt id)
	{
		return null;
	}

	public List<ObscuredInt> ids
	{
		get
		{
			return null;
		}
	}

	private void RefineData(_SoldierSlots src)
	{
	}

	public static void Load(ref SoldierSlots res)
	{
	}
}
