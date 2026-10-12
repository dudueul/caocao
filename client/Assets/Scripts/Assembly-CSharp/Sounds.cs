using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Sounds : PermanentBase
{
	public List<Sound> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Sound> m_sounds;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Sound> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Sound get_Item(ObscuredInt id)
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

	private void RefineData(_Sounds src)
	{
	}

	public static void Load(ref Sounds res)
	{
	}
}
