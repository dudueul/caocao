using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Ranking : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_rankGroups;

	[SerializeField]
	private ObscuredInt m_rankingType;

	[SerializeField]
	private ObscuredInt m_hasReward;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredInt m_orderInGroup;

	[SerializeField]
	private ObscuredInt m_updateType;

	[SerializeField]
	private ObscuredInt m_updateDay;

	[SerializeField]
	private ObscuredInt m_updateHour;

	[SerializeField]
	private ObscuredInt m_usePrevRank;

	[SerializeField]
	private ObscuredInt m_leagueId;

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

	public int rankGroups
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rankingType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int hasReward
	{
		get
		{
			return 0;
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

	public int orderInGroup
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int updateType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int updateDay
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int updateHour
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int usePrevRank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int leagueId
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
