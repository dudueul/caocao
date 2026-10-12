using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class HotTimeReward : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredString m_title;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredInt m_eventRewardType;

	[SerializeField]
	private ObscuredInt m_eventRewardVal;

	[SerializeField]
	private ObscuredInt m_eventRewardID;

	[SerializeField]
	private List<ObscuredInt> m_timeBoxId;

	[SerializeField]
	private List<ObscuredInt> m_eventBoxId;

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

	public int eventRewardType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int eventRewardVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int eventRewardID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> timeBoxId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> eventBoxId
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
