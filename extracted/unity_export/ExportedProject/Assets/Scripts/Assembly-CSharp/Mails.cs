using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class Mails : PermanentBase
{
	public List<Mail> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, Mail> m_mails;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, Mail> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public Mail get_Item(ObscuredInt id)
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

	private void RefineData(_Mails src)
	{
	}

	public static void Load(ref Mails res)
	{
	}
}
