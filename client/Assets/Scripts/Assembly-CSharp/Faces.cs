using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Faces : PermanentBase
{
	public List<Face> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Face> m_faces;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Face> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Face get_Item(ObscuredInt id)
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

	private void RefineData(_Faces src)
	{
	}

	public static void Load(ref Faces res)
	{
	}
}
