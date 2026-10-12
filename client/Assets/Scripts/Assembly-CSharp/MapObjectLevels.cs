using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class MapObjectLevels : PermanentBase
{
	public List<MapObjectLevel> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, MapObjectLevel> m_mapObjectLevels;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, MapObjectLevel> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public MapObjectLevel get_Item(ObscuredInt id)
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

	private void RefineData(_MapObjectLevels src)
	{
	}

	public static void Load(ref MapObjectLevels res)
	{
	}
}
