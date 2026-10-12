using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class CommonMissiontitles : PermanentBase
{
	public List<CommonMissiontitle> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, CommonMissiontitle> m_commonMissiontitles;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, CommonMissiontitle> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public CommonMissiontitle get_Item(ObscuredInt id)
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

	private void RefineData(_CommonMissiontitles src)
	{
	}

	public static void Load(ref CommonMissiontitles res)
	{
	}
}
