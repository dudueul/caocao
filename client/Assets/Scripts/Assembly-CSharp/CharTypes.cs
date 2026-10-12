using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CharTypes : PermanentBase
{
	public List<CharType> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CharType> m_charTypes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CharType> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CharType get_Item(ObscuredInt id)
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

	private void RefineData(_CharTypes src)
	{
	}

	public static void Load(ref CharTypes res)
	{
	}
}
