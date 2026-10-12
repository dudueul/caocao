using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Achievement : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_group;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredInt m_isStack;

	[SerializeField]
	private ObscuredInt m_isHidden;

	[SerializeField]
	private ObscuredInt m_category;

	[SerializeField]
	private ObscuredInt m_checkId;

	[SerializeField]
	private ObscuredInt m_checkType;

	[SerializeField]
	private ObscuredInt m_checkVal;

	[SerializeField]
	private ObscuredInt m_countVal;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private List<ObscuredString> m_gpgsId;

	[SerializeField]
	private ObscuredInt m_enableClassic;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_showToast;

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

	public int isStack
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isHidden
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int category
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int checkId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int checkType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int checkVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int countVal
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

	public List<ObscuredString> gpgsId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int enableClassic
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

	public int showToast
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
