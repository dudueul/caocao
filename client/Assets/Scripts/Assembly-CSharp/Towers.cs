using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Towers : PermanentBase
{
	public List<Tower> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Tower> m_towers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Tower> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Tower get_Item(ObscuredInt id)
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

	private void RefineData(_Towers src)
	{
	}

	public static void Load(ref Towers res)
	{
	}
}
