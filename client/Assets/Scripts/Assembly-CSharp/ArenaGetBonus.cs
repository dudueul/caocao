using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaGetBonus : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private ObscuredInt m_group;

	[SerializeField]
	private ObscuredInt m_passive;

	[SerializeField]
	private ObscuredInt m_treasure;

	[SerializeField]
	private ObscuredInt m_getCoinAmount;

	[SerializeField]
	private ObscuredInt m_isLock;

	[SerializeField]
	private ObscuredInt m_rate;

	[SerializeField]
	private ObscuredInt m_costMin;

	[SerializeField]
	private ObscuredInt m_costMax;

	[SerializeField]
	private ObscuredInt m_shopRate;

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

	public int group
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passive
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int treasure
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getCoinAmount
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

	public int rate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int costMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int costMax
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopRate
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
