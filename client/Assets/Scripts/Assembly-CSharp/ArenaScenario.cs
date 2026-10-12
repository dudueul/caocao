using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaScenario : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredInt m_deckSetting;

	[SerializeField]
	private ObscuredInt m_isLock;

	[SerializeField]
	private ObscuredInt m_price;

	[SerializeField]
	private ObscuredInt m_face;

	[SerializeField]
	private List<ObscuredInt> m_unit;

	[SerializeField]
	private List<ObscuredInt> m_preset;

	[SerializeField]
	private ObscuredString m_country;

	[SerializeField]
	private ObscuredString m_desc;

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

	public int order
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int deckSetting
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isLock
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int price
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int face
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> unit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> preset
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string country
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
}
