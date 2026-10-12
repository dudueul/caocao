using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarBuilding : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_facilityType;

	[SerializeField]
	private ObscuredString m_buildingType;

	[SerializeField]
	private ObscuredInt m_rewardRankPoint;

	[SerializeField]
	private ObscuredInt m_corpsSpawnDistance;

	[SerializeField]
	private ObscuredInt m_buildingBattleData;

	[SerializeField]
	private ObscuredInt m_repairTime;

	[SerializeField]
	private ObscuredInt m_occupyPassiveTime;

	[SerializeField]
	private List<ObscuredInt> m_occupyPassive;

	[SerializeField]
	private ObscuredInt m_faceHeroId;

	[SerializeField]
	private ObscuredInt m_faceGradeType;

	[SerializeField]
	private List<ObscuredString> m_prefab;

	[SerializeField]
	private ObscuredString m_desc;

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

	public int facilityType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string buildingType
	{
		get
		{
			return null;
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

	public int corpsSpawnDistance
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int buildingBattleData
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int repairTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int occupyPassiveTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> occupyPassive
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int faceHeroId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int faceGradeType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredString> prefab
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
