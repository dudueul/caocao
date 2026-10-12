using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Shortcuts : PermanentBase
{
	public List<Shortcut> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Shortcut> m_shortcuts;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Shortcut> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Shortcut get_Item(ObscuredInt id)
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

	private void RefineData(_Shortcuts src)
	{
	}

	public static void Load(ref Shortcuts res)
	{
	}
}
