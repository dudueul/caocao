using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaPreset : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_presetName;

	[SerializeField]
	private ObscuredInt m_group;

	[SerializeField]
	private ObscuredInt m_getUnit;

	[SerializeField]
	private ObscuredInt m_ai;

	[SerializeField]
	private ObscuredInt m_treasureTierMin;

	[SerializeField]
	private ObscuredInt m_treasureTierMax;

	[SerializeField]
	private List<ObscuredInt> m_weapon;

	[SerializeField]
	private List<ObscuredInt> m_armor;

	[SerializeField]
	private List<ObscuredInt> m_kit;

	[SerializeField]
	private ObscuredInt m_passiveTierMin;

	[SerializeField]
	private ObscuredInt m_passiveTierMax;

	[SerializeField]
	private List<ObscuredInt> m_defaultPassive;

	[SerializeField]
	private ObscuredInt m_commonPassiveCount;

	[SerializeField]
	private List<ObscuredInt> m_commonPassive;

	[SerializeField]
	private ObscuredInt m_jobPassiveCount;

	[SerializeField]
	private List<ObscuredInt> m_jobPassive;

	[SerializeField]
	private ObscuredInt m_autoPosition;

	[SerializeField]
	private ObscuredInt m_costMin;

	[SerializeField]
	private ObscuredInt m_costMax;

	[SerializeField]
	private ObscuredInt m_shopRate;

	[SerializeField]
	private ObscuredInt m_nextPreset;

	[SerializeField]
	private ObscuredInt m_upgradeCost;

	[SerializeField]
	private ObscuredInt m_rate;

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

	public string presetName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int group
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int ai
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int treasureTierMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int treasureTierMax
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> weapon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> armor
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> kit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int passiveTierMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passiveTierMax
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> defaultPassive
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int commonPassiveCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> commonPassive
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int jobPassiveCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> jobPassive
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int autoPosition
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int costMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int costMax
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int nextPreset
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int upgradeCost
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rate
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
