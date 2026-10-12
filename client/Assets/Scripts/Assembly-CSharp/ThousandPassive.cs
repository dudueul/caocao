using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ThousandPassive : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_PassiveId;

	[SerializeField]
	private ObscuredInt m_value;

	[SerializeField]
	private ObscuredInt m_Type;

	[SerializeField]
	private ObscuredInt m_Projectile;

	[SerializeField]
	private ObscuredInt m_Magical;

	[SerializeField]
	private ObscuredInt m_Melee;

	[SerializeField]
	private ObscuredInt m_getType;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private List<ObscuredInt> m_exception;

	[SerializeField]
	private List<ObscuredInt> m_jobType;

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

	public int PassiveId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Projectile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Magical
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Melee
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getType
	{
		get
		{
			return 0;
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

	public List<ObscuredInt> exception
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> jobType
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
