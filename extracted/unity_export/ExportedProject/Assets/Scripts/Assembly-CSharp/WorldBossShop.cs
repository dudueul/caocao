using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class WorldBossShop : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_seasonId;

	[SerializeField]
	private ObscuredInt m_grade;

	[SerializeField]
	private ObscuredInt m_mailId;

	[SerializeField]
	private ObscuredInt m_rewardId;

	[SerializeField]
	private ObscuredInt m_stock;

	[SerializeField]
	private ObscuredInt m_cost;

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

	public int seasonId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int grade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mailId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int stock
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cost
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
