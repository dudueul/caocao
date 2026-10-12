using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Instance : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_entry;

	[SerializeField]
	private ObscuredInt m_eventType;

	[SerializeField]
	private ObscuredInt m_difficulty;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_grade;

	[SerializeField]
	private ObscuredString m_prefab;

	[SerializeField]
	private ObscuredString m_forcePrefab;

	[SerializeField]
	private ObscuredInt m_idxAuto;

	[SerializeField]
	private ObscuredInt m_canRepeat;

	[SerializeField]
	private ObscuredInt m_specialType;

	[SerializeField]
	private ObscuredInt m_majorReward;

	[SerializeField]
	private ObscuredInt m_isFlip;

	[SerializeField]
	private ObscuredInt m_state;

	[SerializeField]
	private ObscuredInt m_stateScope;

	[SerializeField]
	private ObscuredInt m_town;

	[SerializeField]
	private ObscuredInt m_x;

	[SerializeField]
	private ObscuredInt m_y;

	[SerializeField]
	private ObscuredInt m_objectiveId;

	[SerializeField]
	private ObscuredInt m_weekday;

	[SerializeField]
	private ObscuredInt m_rank;

	[SerializeField]
	private ObscuredInt m_minRank;

	[SerializeField]
	private ObscuredInt m_maxRank;

	[SerializeField]
	private ObscuredInt m_unitCount;

	[SerializeField]
	private ObscuredInt m_treeCount;

	[SerializeField]
	private List<ObscuredInt> m_unit;

	[SerializeField]
	private List<ObscuredInt> m_item;

	[SerializeField]
	private ObscuredInt m_getFlag;

	[SerializeField]
	private ObscuredInt m_getVal;

	[SerializeField]
	private ObscuredInt m_setFlag;

	[SerializeField]
	private ObscuredInt m_setVal;

	[SerializeField]
	private ObscuredInt m_vipLv;

	[SerializeField]
	private ObscuredInt m_dayCnt;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

	[SerializeField]
	private ObscuredInt m_foodCnt;

	[SerializeField]
	private ObscuredInt m_silverCnt;

	[SerializeField]
	private ObscuredInt m_goldCnt;

	[SerializeField]
	private ObscuredInt m_honorCnt;

	[SerializeField]
	private ObscuredInt m_replay;

	[SerializeField]
	private ObscuredInt m_food;

	[SerializeField]
	private ObscuredInt m_silver;

	[SerializeField]
	private ObscuredInt m_gold;

	[SerializeField]
	private ObscuredInt m_honor;

	[SerializeField]
	private ObscuredInt m_cloud;

	[SerializeField]
	private List<ObscuredInt> m_itemUse;

	[SerializeField]
	private ObscuredInt m_expire;

	[SerializeField]
	private ObscuredInt m_expireFlag;

	[SerializeField]
	private ObscuredInt m_expireFlagVal;

	[SerializeField]
	private ObscuredInt m_coolTime;

	[SerializeField]
	private ObscuredInt m_coolTimeFlag;

	[SerializeField]
	private ObscuredInt m_coolTimeFlagVal;

	[SerializeField]
	private ObscuredString m_eventScript;

	[SerializeField]
	private ObscuredInt m_battlemap;

	[SerializeField]
	private ObscuredInt m_canPassive;

	[SerializeField]
	private ObscuredInt m_mission1;

	[SerializeField]
	private ObscuredInt m_mission2;

	[SerializeField]
	private ObscuredInt m_mission3;

	[SerializeField]
	private ObscuredInt m_type1;

	[SerializeField]
	private ObscuredInt m_reward1;

	[SerializeField]
	private ObscuredInt m_type2;

	[SerializeField]
	private ObscuredInt m_reward2;

	[SerializeField]
	private ObscuredInt m_type3;

	[SerializeField]
	private ObscuredInt m_reward3;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredInt m_time;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredInt m_autoClear;

	[SerializeField]
	private ObscuredInt m_needAutoTicket;

	[SerializeField]
	private ObscuredInt m_country;

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

	public int entry
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int eventType
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

	public string prefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string forcePrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int idxAuto
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canRepeat
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int specialType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int majorReward
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isFlip
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int state
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int stateScope
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int town
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int x
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int y
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int objectiveId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weekday
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rank
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

	public int unitCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int treeCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> unit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> item
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int getFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int setFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int setVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int vipLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int dayCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string startDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string endDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int foodCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silverCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int goldCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int honorCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int replay
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int food
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

	public int cloud
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> itemUse
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int expire
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int expireFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int expireFlagVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int coolTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int coolTimeFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int coolTimeFlagVal
	{
		get
		{
			return 0;
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

	public int battlemap
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canPassive
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mission1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mission2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mission3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type1
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

	public int type2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward3
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

	public int time
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

	public int autoClear
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needAutoTicket
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
}
