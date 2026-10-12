using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaLevel : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_level;

	[SerializeField]
	private ObscuredInt m_isLast;

	[SerializeField]
	private ObscuredInt m_nextId;

	[SerializeField]
	private ObscuredInt m_isBoss;

	[SerializeField]
	private ObscuredInt m_unitLevel;

	[SerializeField]
	private ObscuredInt m_rewardClear;

	[SerializeField]
	private ObscuredInt m_rewardPerfect;

	[SerializeField]
	private List<ObscuredInt> m_rewardMission;

	[SerializeField]
	private ObscuredInt m_refreshType;

	[SerializeField]
	private ObscuredInt m_refreshCost;

	[SerializeField]
	private ObscuredInt m_refreshLimit;

	[SerializeField]
	private ObscuredInt m_shopGroup;

	[SerializeField]
	private ObscuredInt m_shopRate;

	[SerializeField]
	private ObscuredInt m_shopCountMin;

	[SerializeField]
	private ObscuredInt m_shopCountMax;

	[SerializeField]
	private ObscuredInt m_shopUnitMin;

	[SerializeField]
	private ObscuredInt m_shopUnitMax;

	[SerializeField]
	private ObscuredInt m_shopRefreshCost;

	[SerializeField]
	private ObscuredInt m_shopRefreshLimit;

	[SerializeField]
	private ObscuredInt m_getCoinMin;

	[SerializeField]
	private ObscuredInt m_getCoinMax;

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

	public int level
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isLast
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int nextId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isBoss
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardClear
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardPerfect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> rewardMission
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int refreshType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int refreshCost
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int refreshLimit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopGroup
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

	public int shopCountMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopCountMax
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopUnitMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopUnitMax
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopRefreshCost
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopRefreshLimit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getCoinMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getCoinMax
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
