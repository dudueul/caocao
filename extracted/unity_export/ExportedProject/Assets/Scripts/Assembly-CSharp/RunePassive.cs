using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RunePassive : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_passive;

	[SerializeField]
	private List<ObscuredInt> m_val;

	[SerializeField]
	private List<ObscuredInt> m_rate;

	[SerializeField]
	private ObscuredString m_prefix;

	[SerializeField]
	private ObscuredInt m_typeCode;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_minTier;

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

	public int passive
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> val
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> rate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string prefix
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int typeCode
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int country
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int minTier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}
}
