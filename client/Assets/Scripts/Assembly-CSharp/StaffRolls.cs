using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class StaffRolls : PermanentBase
{
	public List<StaffRoll> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, StaffRoll> m_staffRolls;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, StaffRoll> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public StaffRoll get_Item(ObscuredInt id)
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

	private void RefineData(_StaffRolls src)
	{
	}

	public static void Load(ref StaffRolls res)
	{
	}
}
