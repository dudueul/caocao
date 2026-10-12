using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class AutoTexts : PermanentBase
{
	public List<AutoText> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, AutoText> m_autoTexts;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, AutoText> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public AutoText get_Item(ObscuredInt id)
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

	private void RefineData(_AutoTexts src)
	{
	}

	public static void Load(ref AutoTexts res)
	{
	}
}
