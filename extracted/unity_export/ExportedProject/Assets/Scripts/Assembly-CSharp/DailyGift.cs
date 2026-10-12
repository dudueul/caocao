using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class DailyGift : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_giftType;

	[SerializeField]
	private ObscuredInt m_day;

	[SerializeField]
	private ObscuredInt m_eventdailygift;

	[SerializeField]
	private ObscuredInt m_eventgiftgrade;

	[SerializeField]
	private List<ObscuredInt> m_type;

	[SerializeField]
	private List<ObscuredInt> m_reward;

	[SerializeField]
	private List<ObscuredInt> m_mail;

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

	public int giftType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int day
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int eventdailygift
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int eventgiftgrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> type
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> reward
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> mail
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
