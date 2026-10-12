using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarWorld : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredInt m_isFixedReward;

	[SerializeField]
	private ObscuredInt m_combatStartTime;

	[SerializeField]
	private ObscuredInt m_combatEndTime;

	[SerializeField]
	private List<ObscuredInt> m_castleAddPoint;

	[SerializeField]
	private List<ObscuredInt> m_battleAddPoint;

	[SerializeField]
	private ObscuredInt m_reward;

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

	public int isFixedReward
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int combatStartTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int combatEndTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> castleAddPoint
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> battleAddPoint
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int reward
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
