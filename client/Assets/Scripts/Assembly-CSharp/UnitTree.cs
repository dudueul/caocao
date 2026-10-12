using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class UnitTree : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_unlock;

	[SerializeField]
	private ObscuredInt m_state;

	[SerializeField]
	private List<ObscuredInt> m_flag;

	[SerializeField]
	private List<ObscuredInt> m_value;

	[SerializeField]
	private List<ObscuredString> m_desc;

	[SerializeField]
	private ObscuredInt m_silver;

	[SerializeField]
	private ObscuredInt m_gold;

	[SerializeField]
	private ObscuredInt m_honor;

	[SerializeField]
	private ObscuredString m_ticketIcon;

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

	public int unlock
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int state
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> flag
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> value
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredString> desc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int silver
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int gold
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int honor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string ticketIcon
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
