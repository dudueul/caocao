using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Unit : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredString m_name2;

	[SerializeField]
	private ObscuredInt m_heroId;

	[SerializeField]
	private ObscuredString m_battlePrefab;

	[SerializeField]
	private ObscuredString m_bColorR;

	[SerializeField]
	private ObscuredString m_bColorG;

	[SerializeField]
	private ObscuredString m_bColorB;

	[SerializeField]
	private ObscuredString m_eventPrefab;

	[SerializeField]
	private ObscuredString m_eColorR;

	[SerializeField]
	private ObscuredString m_eColorG;

	[SerializeField]
	private ObscuredString m_eColorB;

	[SerializeField]
	private ObscuredInt m_faction;

	[SerializeField]
	private ObscuredInt m_deckType;

	[SerializeField]
	private ObscuredInt m_jobType;

	[SerializeField]
	private ObscuredInt m_level;

	[SerializeField]
	private ObscuredInt m_str;

	[SerializeField]
	private ObscuredInt m_cha;

	[SerializeField]
	private ObscuredInt m_wis;

	[SerializeField]
	private ObscuredInt m_dex;

	[SerializeField]
	private ObscuredInt m_luck;

	[SerializeField]
	private ObscuredInt m_statSum;

	[SerializeField]
	private ObscuredInt m_hp;

	[SerializeField]
	private ObscuredInt m_mp;

	[SerializeField]
	private ObscuredInt m_ep;

	[SerializeField]
	private ObscuredInt m_gold;

	[SerializeField]
	private ObscuredInt m_silver;

	[SerializeField]
	private ObscuredInt m_honor;

	[SerializeField]
	private ObscuredInt m_grade;

	[SerializeField]
	private ObscuredInt m_cost;

	[SerializeField]
	private ObscuredInt m_lordFood;

	[SerializeField]
	private ObscuredInt m_mayorFood;

	[SerializeField]
	private ObscuredInt m_foodLooting;

	[SerializeField]
	private ObscuredInt m_silverLooting;

	[SerializeField]
	private ObscuredInt m_weapon;

	[SerializeField]
	private ObscuredInt m_armor;

	[SerializeField]
	private ObscuredInt m_kit;

	[SerializeField]
	private ObscuredInt m_weaponT;

	[SerializeField]
	private ObscuredInt m_armorT;

	[SerializeField]
	private ObscuredInt m_kitT;

	[SerializeField]
	private ObscuredInt m_priority;

	[SerializeField]
	private List<ObscuredInt> m_passive;

	[SerializeField]
	private List<ObscuredInt> m_passiveVal;

	[SerializeField]
	private ObscuredInt m_dialogSnd;

	[SerializeField]
	private ObscuredInt m_selectSE;

	[SerializeField]
	private ObscuredInt m_groggySE;

	[SerializeField]
	private ObscuredInt m_retreatSE;

	[SerializeField]
	private ObscuredInt m_criticalSE;

	[SerializeField]
	private ObscuredInt m_unitTreeSE;

	[SerializeField]
	private ObscuredInt m_shadowSize;

	[SerializeField]
	private ObscuredInt m_lordSkill;

	[SerializeField]
	private ObscuredInt m_mayorSkill;

	[SerializeField]
	private ObscuredInt m_arenaSynergy;

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

	public string name2
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int heroId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string battlePrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string bColorR
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string bColorG
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string bColorB
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string eventPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string eColorR
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string eColorG
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string eColorB
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int faction
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int deckType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int jobType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int str
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cha
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int wis
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int dex
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int luck
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int statSum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int hp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int ep
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int gold
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silver
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int honor
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

	public int lordFood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mayorFood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int foodLooting
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silverLooting
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weapon
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weaponT
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armorT
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kitT
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int priority
	{
		get
		{
			return 0;
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

	public List<ObscuredInt> passiveVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int dialogSnd
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int selectSE
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int groggySE
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int retreatSE
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int criticalSE
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitTreeSE
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shadowSize
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int lordSkill
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mayorSkill
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int arenaSynergy
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
