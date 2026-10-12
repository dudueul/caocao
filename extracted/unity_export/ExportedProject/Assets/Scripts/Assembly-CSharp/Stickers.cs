using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Stickers : PermanentBase
{
	public List<Sticker> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Sticker> m_stickers;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Sticker> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Sticker get_Item(ObscuredInt id)
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

	private void RefineData(_Stickers src)
	{
	}

	public static void Load(ref Stickers res)
	{
	}
}
