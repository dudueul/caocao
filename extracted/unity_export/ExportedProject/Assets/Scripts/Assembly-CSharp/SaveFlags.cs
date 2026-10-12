using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SaveFlags : PermanentBase
{
	public List<SaveFlag> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SaveFlag> m_saveFlags;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SaveFlag> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SaveFlag get_Item(ObscuredInt id)
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

	private void RefineData(_SaveFlags src)
	{
	}

	public static void Load(ref SaveFlags res)
	{
	}
}
