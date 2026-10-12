using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Tier : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private ObscuredInt m_minPoint;

	[SerializeField]
	private ObscuredInt m_maxPoint;

	[SerializeField]
	private ObscuredInt m_needBattleCount;

	[SerializeField]
	private ObscuredInt m_initPoint;

	[SerializeField]
	private ObscuredInt m_appearNPC;

	[SerializeField]
	private ObscuredInt m_leagueId;

	[SerializeField]
	private ObscuredInt m_npcSettingId;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

	[SerializeField]
	private ObscuredInt m_type1;

	[SerializeField]
	private ObscuredInt m_reward1;

	[SerializeField]
	private List<ObscuredInt> m_resultTimeBox;

	[SerializeField]
	private ObscuredInt m_defeatReward;

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

	public int type
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

	public int tier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int minPoint
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

	public int needBattleCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int initPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int appearNPC
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int leagueId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int npcSettingId
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

	public int type1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> resultTimeBox
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int defeatReward
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
