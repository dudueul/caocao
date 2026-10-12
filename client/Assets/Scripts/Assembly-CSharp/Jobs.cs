using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Jobs : PermanentBase
{
	public List<Job> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Job> m_jobs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Job> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Job get_Item(ObscuredInt id)
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

	private void RefineData(_Jobs src)
	{
	}

	public static void Load(ref Jobs res)
	{
	}
}
