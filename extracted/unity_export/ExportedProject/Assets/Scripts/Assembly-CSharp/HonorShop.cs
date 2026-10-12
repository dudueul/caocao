using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class HonorShop : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_item;

	[SerializeField]
	private ObscuredInt m_lordLv;

	[SerializeField]
	private ObscuredInt m_goodType;

	[SerializeField]
	private ObscuredInt m_cost;

	[SerializeField]
	private ObscuredString m_weekDay;

	[SerializeField]
	private ObscuredInt m_minNum;

	[SerializeField]
	private ObscuredInt m_maxNum;

	[SerializeField]
	private ObscuredInt m_ratio;

	[SerializeField]
	private ObscuredString m_slot;

	[SerializeField]
	private ObscuredInt m_country;

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

	public int lordLv
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

	public int minNum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxNum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int ratio
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string slot
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
}
