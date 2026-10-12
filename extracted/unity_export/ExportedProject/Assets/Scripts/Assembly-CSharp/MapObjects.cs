using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class MapObjects : PermanentBase
{
	public List<MapObject> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, MapObject> m_mapObjects;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, MapObject> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public MapObject get_Item(ObscuredInt id)
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

	private void RefineData(_MapObjects src)
	{
	}

	public static void Load(ref MapObjects res)
	{
	}
}
