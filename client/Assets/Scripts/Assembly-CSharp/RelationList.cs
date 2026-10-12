using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RelationList : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private ObscuredInt m_needStringCount;

	[SerializeField]
	private List<ObscuredInt> m_unitId;

	[SerializeField]
	private ObscuredInt m_unitLevel;

	[SerializeField]
	private List<ObscuredInt> m_mission;

	[SerializeField]
	private ObscuredInt m_missionStoryBook;

	[SerializeField]
	private List<ObscuredInt> m_baseStatType;

	[SerializeField]
	private List<ObscuredInt> m_baseStatVal;

	[SerializeField]
	private List<ObscuredInt> m_isBuff;

	[SerializeField]
	private List<ObscuredInt> m_passiveType;

	[SerializeField]
	private List<ObscuredInt> m_passiveUnitCount;

	[SerializeField]
	private List<ObscuredInt> m_passive;

	[SerializeField]
	private ObscuredString m_scenarioName;

	[SerializeField]
	private ObscuredString m_scenarioImage;

	[SerializeField]
	private ObscuredString m_eventScript;

	[SerializeField]
	private ObscuredInt m_battleMapId;

	[SerializeField]
	private ObscuredString m_battleImage;

	[SerializeField]
	private ObscuredString m_country;

	[SerializeField]
	private ObscuredString m_desc;

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

	public int needStringCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> unitId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int unitLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> mission
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int missionStoryBook
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> baseStatType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> baseStatVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> isBuff
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveUnitCount
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passive
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string scenarioName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string scenarioImage
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string eventScript
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int battleMapId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string battleImage
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string country
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
