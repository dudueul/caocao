using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class SoundGroups : PermanentBase
{
	public List<SoundGroup> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, SoundGroup> m_soundGroups;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, SoundGroup> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public SoundGroup get_Item(ObscuredInt id)
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

	private void RefineData(_SoundGroups src)
	{
	}

	public static void Load(ref SoundGroups res)
	{
	}
}
