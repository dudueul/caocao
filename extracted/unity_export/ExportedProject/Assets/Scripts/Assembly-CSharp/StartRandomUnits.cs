using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class StartRandomUnits : PermanentBase
{
	public List<StartRandomUnit> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, StartRandomUnit> m_startRandomUnits;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, StartRandomUnit> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public StartRandomUnit get_Item(ObscuredInt id)
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

	private void RefineData(_StartRandomUnits src)
	{
	}

	public static void Load(ref StartRandomUnits res)
	{
	}
}
