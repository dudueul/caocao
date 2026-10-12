using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TutorialFlows : PermanentBase
{
	public List<TutorialFlow> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TutorialFlow> m_tutorialFlows;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TutorialFlow> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TutorialFlow get_Item(ObscuredInt id)
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

	private void RefineData(_TutorialFlows src)
	{
	}

	public static void Load(ref TutorialFlows res)
	{
	}
}
