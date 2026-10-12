using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Runes : PermanentBase
{
	public List<Rune> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Rune> m_runes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Rune> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Rune get_Item(ObscuredInt id)
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

	private void RefineData(_Runes src)
	{
	}

	public static void Load(ref Runes res)
	{
	}
}
