using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SetTimeBoxes : PermanentBase
{
	public List<SetTimeBox> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SetTimeBox> m_setTimeBoxes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SetTimeBox> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SetTimeBox get_Item(ObscuredInt id)
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

	private void RefineData(_SetTimeBoxes src)
	{
	}

	public static void Load(ref SetTimeBoxes res)
	{
	}
}
