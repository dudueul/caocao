using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaNPCSetting : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_unit;

	[SerializeField]
	private ObscuredInt m_ai;

	[SerializeField]
	private ObscuredInt m_tileIndex;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredInt m_weapon;

	[SerializeField]
	private ObscuredInt m_armor;

	[SerializeField]
	private ObscuredInt m_kit;

	[SerializeField]
	private List<ObscuredInt> m_passive;

	[SerializeField]
	private ObscuredString m_desc;

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

	public int ai
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tileIndex
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

	public int weapon
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passive
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
