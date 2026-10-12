using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class WorldBossSeason : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_WorldBossID;

	[SerializeField]
	private ObscuredString m_monthKey;

	[SerializeField]
	private ObscuredString m_startTime;

	[SerializeField]
	private ObscuredInt m_offfTime;

	[SerializeField]
	private ObscuredInt m_endTime;

	[SerializeField]
	private ObscuredInt m_reduceCnt;

	[SerializeField]
	private ObscuredInt m_reduceTime;

	[SerializeField]
	private ObscuredInt m_maxPoint;

	[SerializeField]
	private ObscuredInt m_useFlag;

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

	public List<ObscuredInt> WorldBossID
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string monthKey
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string startTime
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int offfTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int endTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reduceCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reduceTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int useFlag
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
}
