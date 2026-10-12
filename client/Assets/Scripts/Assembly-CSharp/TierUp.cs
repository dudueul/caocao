using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TierUp : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_tier;

	[SerializeField]
	private List<ObscuredInt> m_atkUp;

	[SerializeField]
	private List<ObscuredInt> m_defUp;

	[SerializeField]
	private List<ObscuredInt> m_intUp;

	[SerializeField]
	private List<ObscuredInt> m_agiUp;

	[SerializeField]
	private List<ObscuredInt> m_morUp;

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

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

	public List<ObscuredInt> tier
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> atkUp
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> defUp
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> intUp
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> agiUp
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> morUp
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
