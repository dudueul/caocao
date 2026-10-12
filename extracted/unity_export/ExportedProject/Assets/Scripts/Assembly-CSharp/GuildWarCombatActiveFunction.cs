using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarCombatActiveFunction : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_functionCategory;

	[SerializeField]
	private List<ObscuredInt> m_actionId;

	[SerializeField]
	private ObscuredInt m_targetRange;

	[SerializeField]
	private ObscuredInt m_targetType;

	[SerializeField]
	private ObscuredInt m_effectArea;

	[SerializeField]
	private ObscuredInt m_isEscort;

	[SerializeField]
	private ObscuredInt m_isFire;

	[SerializeField]
	private ObscuredInt m_isWater;

	[SerializeField]
	private ObscuredInt m_isEarth;

	[SerializeField]
	private ObscuredInt m_isWind;

	[SerializeField]
	private ObscuredInt m_isRush;

	[SerializeField]
	private ObscuredInt m_isWaterRush;

	[SerializeField]
	private ObscuredInt m_corpsExp;

	[SerializeField]
	private ObscuredInt m_contributionPoint;

	[SerializeField]
	private ObscuredInt m_actionPoint;

	[SerializeField]
	private ObscuredInt m_coolTime;

	[SerializeField]
	private ObscuredInt m_functionTypeId;

	[SerializeField]
	private ObscuredString m_atlas;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_desc;

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

	public int functionCategory
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> actionId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int targetRange
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int targetType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int effectArea
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isEscort
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isFire
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isWater
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isEarth
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isWind
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isRush
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isWaterRush
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsExp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int contributionPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int actionPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int coolTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int functionTypeId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string atlas
	{
		get
		{
			return null;
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
}
