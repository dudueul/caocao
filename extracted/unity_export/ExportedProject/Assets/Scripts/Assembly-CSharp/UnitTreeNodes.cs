using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class UnitTreeNodes : PermanentBase
{
	public List<UnitTreeNode> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, UnitTreeNode> m_unitTreeNodes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, UnitTreeNode> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UnitTreeNode get_Item(ObscuredInt id)
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

	private void RefineData(_UnitTreeNodes src)
	{
	}

	public static void Load(ref UnitTreeNodes res)
	{
	}
}
