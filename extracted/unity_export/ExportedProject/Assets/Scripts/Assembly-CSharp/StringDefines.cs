using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class StringDefines : PermanentBase
{
	public List<StringDefine> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, StringDefine> m_stringDefines;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, StringDefine> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public StringDefine get_Item(ObscuredInt id)
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

	private void RefineData(_StringDefines src)
	{
	}

	public static void Load(ref StringDefines res)
	{
	}
}
