using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SeasonPass : PermanentBase
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
	private ObscuredString m_warningDesc;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

	[SerializeField]
	private ObscuredInt m_maxLevel;

	[SerializeField]
	private ObscuredInt m_maxExp;

	[SerializeField]
	private ObscuredInt m_getTrainPoint;

	[SerializeField]
	private ObscuredInt m_getPaidPoint;

	[SerializeField]
	private ObscuredString m_textMessage;

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

	public string warningDesc
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

	public int maxLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxExp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getTrainPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getPaidPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string textMessage
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
