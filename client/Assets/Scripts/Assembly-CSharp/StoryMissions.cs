using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class StoryMissions : PermanentBase
{
	public List<StoryMission> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, StoryMission> m_storyMissions;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, StoryMission> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public StoryMission get_Item(ObscuredInt id)
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

	private void RefineData(_StoryMissions src)
	{
	}

	public static void Load(ref StoryMissions res)
	{
	}
}
