using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TimeBox : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_infoType;

	[SerializeField]
	private ObscuredInt m_isVisible;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredString m_perDesc;

	[SerializeField]
	private List<ObscuredString> m_desc;

	[SerializeField]
	private List<ObscuredInt> m_fixedRewardType;

	[SerializeField]
	private List<ObscuredInt> m_fixedRewardId;

	[SerializeField]
	private ObscuredInt m_openTime;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_boxEffect;

	[SerializeField]
	private ObscuredInt m_alram;

	[SerializeField]
	private ObscuredInt m_drop;

	[SerializeField]
	private ObscuredInt m_limit;

	[SerializeField]
	private ObscuredInt m_limitCount;

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

	public int infoType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isVisible
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

	public string perDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredString> desc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fixedRewardType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fixedRewardId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int openTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string icon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string boxEffect
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int alram
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int drop
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int limit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int limitCount
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
