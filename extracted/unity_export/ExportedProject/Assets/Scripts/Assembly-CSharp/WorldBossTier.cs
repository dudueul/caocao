using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class WorldBossTier : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_seasonId;

	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_grade;

	[SerializeField]
	private ObscuredInt m_rankType;

	[SerializeField]
	private ObscuredInt m_rankMin;

	[SerializeField]
	private ObscuredInt m_rankMax;

	[SerializeField]
	private ObscuredInt m_maxRewardStock;

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

	public int rankType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rankMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rankMax
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxRewardStock
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
