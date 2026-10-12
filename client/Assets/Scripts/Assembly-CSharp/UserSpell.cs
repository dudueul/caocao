using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class UserSpell : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_cost;

	[SerializeField]
	private List<ObscuredInt> m_effectTarget;

	[SerializeField]
	private List<ObscuredInt> m_targetAmount;

	[SerializeField]
	private ObscuredInt m_effectFunc;

	[SerializeField]
	private ObscuredInt m_effectValue;

	[SerializeField]
	private ObscuredString m_icon;

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

	public int cost
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectTarget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> targetAmount
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int effectFunc
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int effectValue
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
}
