using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Tiles : PermanentBase
{
	public List<Tile> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Tile> m_tiles;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Tile> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Tile get_Item(ObscuredInt id)
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

	private void RefineData(_Tiles src)
	{
	}

	public static void Load(ref Tiles res)
	{
	}
}
