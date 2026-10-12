using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class StickerSets : PermanentBase
{
	public List<StickerSet> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, StickerSet> m_stickerSets;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, StickerSet> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public StickerSet get_Item(ObscuredInt id)
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

	private void RefineData(_StickerSets src)
	{
	}

	public static void Load(ref StickerSets res)
	{
	}
}
