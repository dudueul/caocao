using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class StoryStampBoxes : PermanentBase
{
	public List<StoryStampBox> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, StoryStampBox> m_storyStampBoxes;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, StoryStampBox> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public StoryStampBox get_Item(ObscuredInt id)
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

	private void RefineData(_StoryStampBoxes src)
	{
	}

	public static void Load(ref StoryStampBoxes res)
	{
	}
}
