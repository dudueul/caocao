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
			return m_name;
		}
		set
		{
			m_name = value;
		}
	}

	public int id
	{
		get
		{
			return m_id;
		}
		set
		{
			m_id = value;
		}
	}

	public int tier
	{
		get
		{
			return m_tier;
		}
		set
		{
			m_tier = value;
		}
	}

	public int slot
	{
		get
		{
			return m_slot;
		}
		set
		{
			m_slot = value;
		}
	}

	public int itemType
	{
		get
		{
			return m_itemType;
		}
		set
		{
			m_itemType = value;
		}
	}

	public int boxType
	{
		get
		{
			return m_boxType;
		}
		set
		{
			m_boxType = value;
		}
	}

	public int price
	{
		get
		{
			return m_price;
		}
		set
		{
			m_price = value;
		}
	}

	public int gold
	{
		get
		{
			return m_gold;
		}
		set
		{
			m_gold = value;
		}
	}

	public int tresureType
	{
		get
		{
			return m_tresureType;
		}
		set
		{
			m_tresureType = value;
		}
	}

	public int enhanceType
	{
		get
		{
			return m_enhanceType;
		}
		set
		{
			m_enhanceType = value;
		}
	}

	public int unitLv
	{
		get
		{
			return m_unitLv;
		}
		set
		{
			m_unitLv = value;
		}
	}

	public int itemEffect
	{
		get
		{
			return m_itemEffect;
		}
		set
		{
			m_itemEffect = value;
		}
	}

	public int effectValue
	{
		get
		{
			return m_effectValue;
		}
		set
		{
			m_effectValue = value;
		}
	}

	public int maxValue
	{
		get
		{
			return m_maxValue;
		}
		set
		{
			m_maxValue = value;
		}
	}

	public int typeValue
	{
		get
		{
			return m_typeValue;
		}
		set
		{
			m_typeValue = value;
		}
	}

	public int payType
	{
		get
		{
			return m_payType;
		}
		set
		{
			m_payType = value;
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
			return m_skillId;
		}
		set
		{
			m_skillId = value;
		}
	}

	public int skillVal
	{
		get
		{
			return m_skillVal;
		}
		set
		{
			m_skillVal = value;
		}
	}

	public int unitId
	{
		get
		{
			return m_unitId;
		}
		set
		{
			m_unitId = value;
		}
	}

	public string atlas
	{
		get
		{
			return m_atlas;
		}
		set
		{
			m_atlas = value;
		}
	}

	public string icon
	{
		get
		{
			return m_icon;
		}
		set
		{
			m_icon = value;
		}
	}

	public int atk
	{
		get
		{
			return m_atk;
		}
		set
		{
			m_atk = value;
		}
	}

	public int atkUp
	{
		get
		{
			return m_atkUp;
		}
		set
		{
			m_atkUp = value;
		}
	}

	public int def
	{
		get
		{
			return m_def;
		}
		set
		{
			m_def = value;
		}
	}

	public int defUp
	{
		get
		{
			return m_defUp;
		}
		set
		{
			m_defUp = value;
		}
	}

	public int inte
	{
		get
		{
			return m_inte;
		}
		set
		{
			m_inte = value;
		}
	}

	public int inteUp
	{
		get
		{
			return m_inteUp;
		}
		set
		{
			m_inteUp = value;
		}
	}

	public int agi
	{
		get
		{
			return m_agi;
		}
		set
		{
			m_agi = value;
		}
	}

	public int agiUp
	{
		get
		{
			return m_agiUp;
		}
		set
		{
			m_agiUp = value;
		}
	}

	public int mor
	{
		get
		{
			return m_mor;
		}
		set
		{
			m_mor = value;
		}
	}

	public int morUp
	{
		get
		{
			return m_morUp;
		}
		set
		{
			m_morUp = value;
		}
	}

	public int hp
	{
		get
		{
			return m_hp;
		}
		set
		{
			m_hp = value;
		}
	}

	public int hpUp
	{
		get
		{
			return m_hpUp;
		}
		set
		{
			m_hpUp = value;
		}
	}

	public int mp
	{
		get
		{
			return m_mp;
		}
		set
		{
			m_mp = value;
		}
	}

	public int mpUp
	{
		get
		{
			return m_mpUp;
		}
		set
		{
			m_mpUp = value;
		}
	}

	public int maxLv
	{
		get
		{
			return m_maxLv;
		}
		set
		{
			m_maxLv = value;
		}
	}

	public int targetType
	{
		get
		{
			return m_targetType;
		}
		set
		{
			m_targetType = value;
		}
	}

	public int targetArea
	{
		get
		{
			return m_targetArea;
		}
		set
		{
			m_targetArea = value;
		}
	}

	public int effectArea
	{
		get
		{
			return m_effectArea;
		}
		set
		{
			m_effectArea = value;
		}
	}

	public int canUseThousandBattle
	{
		get
		{
			return m_canUseThousandBattle;
		}
		set
		{
			m_canUseThousandBattle = value;
		}
	}

	public int canUseWorldBoss
	{
		get
		{
			return m_canUseWorldBoss;
		}
		set
		{
			m_canUseWorldBoss = value;
		}
	}

	public int skinId
	{
		get
		{
			return m_skinId;
		}
		set
		{
			m_skinId = value;
		}
	}

	public int stickerId
	{
		get
		{
			return m_stickerId;
		}
		set
		{
			m_stickerId = value;
		}
	}

	public int jobType
	{
		get
		{
			return m_jobType;
		}
		set
		{
			m_jobType = value;
		}
	}

	public int skillBook
	{
		get
		{
			return m_skillBook;
		}
		set
		{
			m_skillBook = value;
		}
	}

	public int country
	{
		get
		{
			return m_country;
		}
		set
		{
			m_country = value;
		}
	}

	public string desc
	{
		get
		{
			return m_desc;
		}
		set
		{
			m_desc = value;
		}
	}
}
