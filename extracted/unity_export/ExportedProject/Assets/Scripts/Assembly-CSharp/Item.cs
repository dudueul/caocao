using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Item : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private ObscuredInt m_slot;

	[SerializeField]
	private ObscuredInt m_itemType;

	[SerializeField]
	private ObscuredInt m_boxType;

	[SerializeField]
	private ObscuredInt m_price;

	[SerializeField]
	private ObscuredInt m_gold;

	[SerializeField]
	private ObscuredInt m_tresureType;

	[SerializeField]
	private ObscuredInt m_enhanceType;

	[SerializeField]
	private ObscuredInt m_unitLv;

	[SerializeField]
	private ObscuredInt m_itemEffect;

	[SerializeField]
	private ObscuredInt m_effectValue;

	[SerializeField]
	private ObscuredInt m_maxValue;

	[SerializeField]
	private ObscuredInt m_typeValue;

	[SerializeField]
	private ObscuredInt m_payType;

	[SerializeField]
	private List<ObscuredInt> m_passive;

	[SerializeField]
	private List<ObscuredInt> m_passiveVal;

	[SerializeField]
	private ObscuredInt m_skillId;

	[SerializeField]
	private ObscuredInt m_skillVal;

	[SerializeField]
	private ObscuredInt m_unitId;

	[SerializeField]
	private ObscuredString m_atlas;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredInt m_atk;

	[SerializeField]
	private ObscuredInt m_atkUp;

	[SerializeField]
	private ObscuredInt m_def;

	[SerializeField]
	private ObscuredInt m_defUp;

	[SerializeField]
	private ObscuredInt m_inte;

	[SerializeField]
	private ObscuredInt m_inteUp;

	[SerializeField]
	private ObscuredInt m_agi;

	[SerializeField]
	private ObscuredInt m_agiUp;

	[SerializeField]
	private ObscuredInt m_mor;

	[SerializeField]
	private ObscuredInt m_morUp;

	[SerializeField]
	private ObscuredInt m_hp;

	[SerializeField]
	private ObscuredInt m_hpUp;

	[SerializeField]
	private ObscuredInt m_mp;

	[SerializeField]
	private ObscuredInt m_mpUp;

	[SerializeField]
	private ObscuredInt m_maxLv;

	[SerializeField]
	private ObscuredInt m_targetType;

	[SerializeField]
	private ObscuredInt m_targetArea;

	[SerializeField]
	private ObscuredInt m_effectArea;

	[SerializeField]
	private ObscuredInt m_canUseThousandBattle;

	[SerializeField]
	private ObscuredInt m_canUseWorldBoss;

	[SerializeField]
	private ObscuredInt m_skinId;

	[SerializeField]
	private ObscuredInt m_stickerId;

	[SerializeField]
	private ObscuredInt m_jobType;

	[SerializeField]
	private ObscuredInt m_skillBook;

	[SerializeField]
	private ObscuredInt m_country;

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

	public int slot
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int boxType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int price
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

	public int tresureType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int enhanceType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemEffect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int effectValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int payType
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

	public int skillId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int skillVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string atlas
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string icon
	{
		get
		{
			return null;
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

	public int atkUp
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

	public int defUp
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

	public int inteUp
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

	public int agiUp
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

	public int morUp
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

	public int hpUp
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

	public int mpUp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int targetType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int targetArea
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int effectArea
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canUseThousandBattle
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canUseWorldBoss
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int skinId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int stickerId
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

	public int skillBook
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int country
	{
		get
		{
			return 0;
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
