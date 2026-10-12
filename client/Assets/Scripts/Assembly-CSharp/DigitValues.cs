using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class DigitValues : PermanentBase
{
	public List<DigitValue> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, DigitValue> m_digitValues;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, DigitValue> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public DigitValue get_Item(ObscuredInt id)
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

	private void RefineData(_DigitValues src)
	{
	}

	public static void Load(ref DigitValues res)
	{
	}
}
