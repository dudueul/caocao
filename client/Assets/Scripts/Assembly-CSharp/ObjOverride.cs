using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ObjOverride : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_state1;

	[SerializeField]
	private List<ObscuredInt> m_LV1;

	[SerializeField]
	private List<ObscuredInt> m_objId1;

	[SerializeField]
	private ObscuredInt m_state2;

	[SerializeField]
	private List<ObscuredInt> m_LV2;

	[SerializeField]
	private List<ObscuredInt> m_objId2;

	public int id
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int state1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> LV1
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> objId1
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int state2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> LV2
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> objId2
	{
		get
		{
			return null;
		}
		set
		{
		}
	}
}
