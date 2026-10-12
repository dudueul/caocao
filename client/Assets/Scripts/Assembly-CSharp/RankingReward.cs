using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RankingReward : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_rankingsID;

	[SerializeField]
	private ObscuredInt m_rankRangeType;

	[SerializeField]
	private ObscuredInt m_leagueId;

	[SerializeField]
	private ObscuredInt m_minVal;

	[SerializeField]
	private ObscuredInt m_maxVal;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private List<ObscuredInt> m_resultTimeBox;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

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

	public int rankingsID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rankRangeType
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

	public int minVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> resultTimeBox
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
}
