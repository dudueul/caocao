using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarBuildingBattleData : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_hP;

	[SerializeField]
	private ObscuredInt m_atk;

	[SerializeField]
	private ObscuredInt m_def;

	[SerializeField]
	private ObscuredInt m_inte;

	[SerializeField]
	private ObscuredInt m_agi;

	[SerializeField]
	private ObscuredInt m_mor;

	[SerializeField]
	private ObscuredInt m_baseDmg;

	[SerializeField]
	private ObscuredInt m_sight;

	[SerializeField]
	private List<ObscuredInt> m_passiveListId;

	[SerializeField]
	private ObscuredInt m_autoFunctionId;

	[SerializeField]
	private ObscuredInt m_autoFunctionCoolTime;

	[SerializeField]
	private ObscuredInt m_autoMaxRange;

	[SerializeField]
	private ObscuredInt m_counterFunctionId;

	[SerializeField]
	private ObscuredInt m_counterMaxRange;

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

	public int hP
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int atk
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int def
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int inte
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int agi
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int baseDmg
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int sight
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveListId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int autoFunctionId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int autoFunctionCoolTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int autoMaxRange
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int counterFunctionId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int counterMaxRange
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
