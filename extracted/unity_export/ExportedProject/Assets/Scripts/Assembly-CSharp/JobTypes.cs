using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class JobTypes : PermanentBase
{
	public List<JobType> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, JobType> m_jobTypes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, JobType> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public JobType get_Item(ObscuredInt id)
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

	private void RefineData(_JobTypes src)
	{
	}

	public static void Load(ref JobTypes res)
	{
	}
}
