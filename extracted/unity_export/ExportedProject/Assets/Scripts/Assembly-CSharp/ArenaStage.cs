using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaStage : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_theme;

	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_battleMap;

	[SerializeField]
	private List<ObscuredInt> m_tile;

	[SerializeField]
	private ObscuredString m_eventScript;

	[SerializeField]
	private ObscuredString m_battleMapImage;

	[SerializeField]
	private ObscuredInt m_countUnit;

	[SerializeField]
	private ObscuredInt m_turn;

	[SerializeField]
	private ObscuredInt m_npcFormation;

	[SerializeField]
	private ObscuredInt m_enemyFace;

	[SerializeField]
	private ObscuredInt m_weather;

	[SerializeField]
	private List<ObscuredInt> m_displayWeather;

	[SerializeField]
	private List<ObscuredInt> m_globalPassiveType;

	[SerializeField]
	private List<ObscuredInt> m_globalPassive;

	[SerializeField]
	private List<ObscuredInt> m_mission;

	[SerializeField]
	private List<ObscuredInt> m_presetType;

	[SerializeField]
	private List<ObscuredInt> m_bonusGroup;

	[SerializeField]
	private List<ObscuredInt> m_getPresetGroupMin;

	[SerializeField]
	private List<ObscuredInt> m_getPresetGroupMax;

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

	public int theme
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

	public int battleMap
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> tile
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

	public string battleMapImage
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int countUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int turn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int npcFormation
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int enemyFace
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weather
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> displayWeather
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> globalPassiveType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> globalPassive
	{
		get
		{
			return null;
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

	public List<ObscuredInt> presetType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> bonusGroup
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> getPresetGroupMin
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> getPresetGroupMax
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
