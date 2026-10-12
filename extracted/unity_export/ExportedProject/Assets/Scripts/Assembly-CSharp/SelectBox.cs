using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SelectBox : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_boxType;

	[SerializeField]
	private ObscuredInt m_boxOption;

	[SerializeField]
	private ObscuredInt m_bonusBoxType;

	[SerializeField]
	private List<ObscuredInt> m_refreshcost;

	[SerializeField]
	private ObscuredString m_boxEffect;

	[SerializeField]
	private ObscuredString m_title;

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

	public int boxType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int boxOption
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int bonusBoxType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> refreshcost
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string boxEffect
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string title
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
