using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TutorialUIs : PermanentBase
{
	public List<TutorialUI> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TutorialUI> m_tutorialUIs;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TutorialUI> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TutorialUI get_Item(ObscuredInt id)
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

	private void RefineData(_TutorialUIs src)
	{
	}

	public static void Load(ref TutorialUIs res)
	{
	}
}
