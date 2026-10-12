using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class LinkedGoods : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_title;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_cycleCount;

	[SerializeField]
	private List<ObscuredInt> m_shopType;

	[SerializeField]
	private List<ObscuredInt> m_productID;

	[SerializeField]
	private List<ObscuredInt> m_saleCount;

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

	public string startDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string endDate
	{
		get
		{
			return null;
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

	public int cycleCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> shopType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> productID
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> saleCount
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
