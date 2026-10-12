using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class LordEffect : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_effectType;

	[SerializeField]
	private ObscuredInt m_effectRange;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private List<ObscuredInt> m_level;

	[SerializeField]
	private List<ObscuredInt> m_effectVal;

	[SerializeField]
	private List<ObscuredInt> m_effectTime;

	[SerializeField]
	private List<ObscuredInt> m_coolTime;

	[SerializeField]
	private List<ObscuredInt> m_silver;

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

	public int effectType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int effectRange
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string icon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string desc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> level
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectTime
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> coolTime
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> silver
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
