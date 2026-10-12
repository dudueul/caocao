using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SeasonPassReward : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_seasonPassID;

	[SerializeField]
	private ObscuredInt m_rewardType;

	[SerializeField]
	private ObscuredInt m_rewardVal;

	[SerializeField]
	private ObscuredInt m_costType;

	[SerializeField]
	private ObscuredInt m_costVal;

	[SerializeField]
	private ObscuredInt m_rewardID;

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

	public int seasonPassID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int costType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int costVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardID
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
