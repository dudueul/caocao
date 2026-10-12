using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SetGood : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_count;

	[SerializeField]
	private List<ObscuredInt> m_good;

	[SerializeField]
	private List<ObscuredInt> m_min;

	[SerializeField]
	private List<ObscuredInt> m_max;

	[SerializeField]
	private List<ObscuredInt> m_rate;

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

	public int type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int count
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> good
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> min
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> max
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
}
