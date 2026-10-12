using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWeeklyMission : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_isUsed;

	[SerializeField]
	private ObscuredInt m_enableClassic;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_checkType;

	[SerializeField]
	private List<ObscuredInt> m_checkVal;

	[SerializeField]
	private List<ObscuredInt> m_checkCompare;

	[SerializeField]
	private ObscuredInt m_countVal;

	[SerializeField]
	private ObscuredInt m_maxCount;

	[SerializeField]
	private ObscuredInt m_soloRewardType;

	[SerializeField]
	private ObscuredInt m_soloReward;

	[SerializeField]
	private ObscuredInt m_masterRewardType;

	[SerializeField]
	private ObscuredInt m_masterReward;

	[SerializeField]
	private ObscuredInt m_adminRewardType;

	[SerializeField]
	private ObscuredInt m_adminReward;

	[SerializeField]
	private ObscuredInt m_guildRewardType;

	[SerializeField]
	private ObscuredInt m_guildReward;

	[SerializeField]
	private ObscuredInt m_slot;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredString m_summary;

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

	public int isUsed
	{
		get
		{
			return 0;
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

	public List<ObscuredInt> checkVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> checkCompare
	{
		get
		{
			return null;
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

	public int maxCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int soloRewardType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int soloReward
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int masterRewardType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int masterReward
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int adminRewardType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int adminReward
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int guildRewardType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int guildReward
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int slot
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

	public string summary
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
