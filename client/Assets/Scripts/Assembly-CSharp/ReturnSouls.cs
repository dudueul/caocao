using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ReturnSouls : PermanentBase
{
	public List<ReturnSoul> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ReturnSoul> m_returnSouls;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ReturnSoul> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ReturnSoul get_Item(ObscuredInt id)
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

	private void RefineData(_ReturnSouls src)
	{
	}

	public static void Load(ref ReturnSouls res)
	{
	}
}
