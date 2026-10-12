using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Storybook : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_image;

	[SerializeField]
	private ObscuredInt m_preStorybook;

	[SerializeField]
	private ObscuredInt m_classicModeFlag;

	[SerializeField]
	private ObscuredInt m_goodsType;

	[SerializeField]
	private ObscuredInt m_price;

	[SerializeField]
	private ObscuredInt m_gold;

	[SerializeField]
	private ObscuredInt m_language;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_classicCountry;

	[SerializeField]
	private ObscuredInt m_rollBack;

	[SerializeField]
	private ObscuredInt m_serialization;

	[SerializeField]
	private ObscuredString m_dayofWeek;

	[SerializeField]
	private List<ObscuredInt> m_needUnit;

	[SerializeField]
	private List<ObscuredInt> m_whitelist;

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

	public string image
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int preStorybook
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int classicModeFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int goodsType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int price
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int gold
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int language
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

	public int classicCountry
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rollBack
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int serialization
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string dayofWeek
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> needUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> whitelist
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
