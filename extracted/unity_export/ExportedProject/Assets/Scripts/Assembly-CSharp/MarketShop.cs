using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class MarketShop : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_lv;

	[SerializeField]
	private ObscuredString m_weekDay;

	[SerializeField]
	private List<ObscuredInt> m_itemId;

	[SerializeField]
	private List<ObscuredInt> m_goodType;

	[SerializeField]
	private List<ObscuredInt> m_cost;

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

	public int lv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string weekDay
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> itemId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> goodType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> cost
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
