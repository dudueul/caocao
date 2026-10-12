using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Tech : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_Tab;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredInt m_group;

	[SerializeField]
	private ObscuredInt m_positionX;

	[SerializeField]
	private ObscuredInt m_positionY;

	[SerializeField]
	private ObscuredInt m_previous;

	[SerializeField]
	private ObscuredInt m_maxLv;

	[SerializeField]
	private List<ObscuredInt> m_cType;

	[SerializeField]
	private List<ObscuredInt> m_cVal;

	[SerializeField]
	private List<ObscuredInt> m_needRank;

	[SerializeField]
	private List<ObscuredInt> m_val;

	[SerializeField]
	private ObscuredInt m_descType;

	[SerializeField]
	private ObscuredString m_effectDesc;

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

	public int Tab
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

	public int positionX
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int positionY
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int previous
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> cType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> cVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> needRank
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> val
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int descType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string effectDesc
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
