using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarTown : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_grade;

	[SerializeField]
	private ObscuredInt m_rewardRankPoint;

	[SerializeField]
	private List<ObscuredInt> m_attackerAmount;

	[SerializeField]
	private List<ObscuredInt> m_defenderAmount;

	[SerializeField]
	private ObscuredString m_mapScript;

	[SerializeField]
	private List<ObscuredInt> m_mainTileType;

	[SerializeField]
	private List<ObscuredInt> m_linkedTown;

	[SerializeField]
	private ObscuredInt m_townRewardId;

	[SerializeField]
	private ObscuredInt m_ownerCheck;

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

	public int rewardRankPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> attackerAmount
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> defenderAmount
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string mapScript
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> mainTileType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> linkedTown
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int townRewardId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int ownerCheck
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
