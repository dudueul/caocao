using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ScenarioFlags : PermanentBase
{
	public List<ScenarioFlag> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ScenarioFlag> m_scenarioFlags;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ScenarioFlag> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ScenarioFlag get_Item(ObscuredInt id)
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

	private void RefineData(_ScenarioFlags src)
	{
	}

	public static void Load(ref ScenarioFlags res)
	{
	}
}
