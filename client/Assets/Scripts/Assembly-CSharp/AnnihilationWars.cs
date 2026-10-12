using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class AnnihilationWars : PermanentBase
{
	public List<AnnihilationWar> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, AnnihilationWar> m_annihilationWars;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, AnnihilationWar> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public AnnihilationWar get_Item(ObscuredInt id)
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

	private void RefineData(_AnnihilationWars src)
	{
	}

	public static void Load(ref AnnihilationWars res)
	{
	}
}
