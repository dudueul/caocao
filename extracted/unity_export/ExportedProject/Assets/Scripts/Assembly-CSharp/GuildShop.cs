using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildShop : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_item;

	[SerializeField]
	private ObscuredInt m_openGrade;

	[SerializeField]
	private ObscuredInt m_goodType;

	[SerializeField]
	private ObscuredInt m_cost;

	[SerializeField]
	private List<ObscuredInt> m_num;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

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

	public int item
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int openGrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int goodType
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

	public List<ObscuredInt> num
	{
		get
		{
			return null;
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
}
