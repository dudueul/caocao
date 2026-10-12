using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class ObjOverrides : PermanentBase
{
	public List<ObjOverride> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, ObjOverride> m_objOverrides;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, ObjOverride> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public ObjOverride get_Item(ObscuredInt id)
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

	private void RefineData(_ObjOverrides src)
	{
	}

	public static void Load(ref ObjOverrides res)
	{
	}
}
