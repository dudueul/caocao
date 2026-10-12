using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class UnitRank : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_minPoint;

	[SerializeField]
	private ObscuredInt m_rankValue;

	[SerializeField]
	private ObscuredInt m_rankPercent;

	[SerializeField]
	private List<ObscuredInt> m_rankUpItem;

	[SerializeField]
	private List<ObscuredInt> m_rankUpItemCount;

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

	public int tier
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

	public int minPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rankValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rankPercent
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> rankUpItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> rankUpItemCount
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
