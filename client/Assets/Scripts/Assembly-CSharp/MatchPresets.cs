using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class MatchPresets : PermanentBase
{
	public List<MatchPreset> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, MatchPreset> m_matchPresets;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, MatchPreset> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public MatchPreset get_Item(ObscuredInt id)
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

	private void RefineData(_MatchPresets src)
	{
	}

	public static void Load(ref MatchPresets res)
	{
	}
}
