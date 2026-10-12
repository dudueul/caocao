using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SelectBoxes : PermanentBase
{
	public List<SelectBox> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SelectBox> m_selectBoxes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SelectBox> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SelectBox get_Item(ObscuredInt id)
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

	private void RefineData(_SelectBoxes src)
	{
	}

	public static void Load(ref SelectBoxes res)
	{
	}
}
