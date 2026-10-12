using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Vips : PermanentBase
{
	public List<Vip> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Vip> m_vips;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Vip> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Vip get_Item(ObscuredInt id)
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

	private void RefineData(_Vips src)
	{
	}

	public static void Load(ref Vips res)
	{
	}
}
