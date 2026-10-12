using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Storybooks : PermanentBase
{
	public List<Storybook> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Storybook> m_storybooks;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Storybook> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Storybook get_Item(ObscuredInt id)
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

	private void RefineData(_Storybooks src)
	{
	}

	public static void Load(ref Storybooks res)
	{
	}
}
