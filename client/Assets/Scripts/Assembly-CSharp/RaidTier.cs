using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RaidTier : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_difficulty;

	[SerializeField]
	private ObscuredInt m_rankType;

	[SerializeField]
	private ObscuredInt m_minRank;

	[SerializeField]
	private ObscuredInt m_maxRank;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredInt m_reward1;

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

	public int difficulty
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

	public int minRank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxRank
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
}
