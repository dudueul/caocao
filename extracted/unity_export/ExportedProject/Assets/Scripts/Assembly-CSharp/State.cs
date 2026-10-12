using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class State : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_occupationtype0;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredInt m_flag;

	[SerializeField]
	private ObscuredInt m_dailyMail;

	[SerializeField]
	private ObscuredInt m_cloud;

	[SerializeField]
	private ObscuredString m_forceColor;

	[SerializeField]
	private ObscuredInt m_boss;

	[SerializeField]
	private ObscuredInt m_pedlarFlag;

	[SerializeField]
	private List<ObscuredInt> m_pedlarVal;

	[SerializeField]
	private ObscuredInt m_instanceMinLv;

	[SerializeField]
	private ObscuredInt m_instanceMaxLv;

	[SerializeField]
	private ObscuredString m_riotEvent;

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

	public int occupationtype0
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

	public int flag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int dailyMail
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cloud
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string forceColor
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int boss
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int pedlarFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> pedlarVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int instanceMinLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int instanceMaxLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string riotEvent
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
