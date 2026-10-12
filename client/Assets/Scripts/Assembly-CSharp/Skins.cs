using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Skins : PermanentBase
{
	public List<Skin> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Skin> m_skins;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Skin> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Skin get_Item(ObscuredInt id)
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

	private void RefineData(_Skins src)
	{
	}

	public static void Load(ref Skins res)
	{
	}
}
