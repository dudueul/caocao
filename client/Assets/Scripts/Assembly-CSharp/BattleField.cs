using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class BattleField : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_defUni;

	[SerializeField]
	private ObscuredString m_prefab;

	[SerializeField]
	private ObscuredInt m_isFlip;

	[SerializeField]
	private ObscuredInt m_state;

	[SerializeField]
	private ObscuredInt m_cloud;

	[SerializeField]
	private ObscuredInt m_position_X;

	[SerializeField]
	private ObscuredInt m_position_Y;

	[SerializeField]
	private ObscuredInt m_battleFieldDifficulty;

	[SerializeField]
	private ObscuredInt m_seizeTurn;

	[SerializeField]
	private ObscuredInt m_tacticCounts;

	[SerializeField]
	private ObscuredInt m_battleMap;

	[SerializeField]
	private List<ObscuredInt> m_tileId;

	[SerializeField]
	private ObscuredInt m_battleTime;

	[SerializeField]
	private ObscuredInt m_baseOccupation;

	[SerializeField]
	private ObscuredInt m_maxOccupation;

	[SerializeField]
	private ObscuredInt m_productionType0;

	[SerializeField]
	private ObscuredInt m_productionValue0;

	[SerializeField]
	private ObscuredInt m_productionType1;

	[SerializeField]
	private ObscuredInt m_productionValue1;

	[SerializeField]
	private ObscuredString m_npcName;

	[SerializeField]
	private ObscuredInt m_npcRank;

	[SerializeField]
	private ObscuredInt m_npcUnitLv;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

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

	public int defUni
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

	public int position_X
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int position_Y
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int battleFieldDifficulty
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int seizeTurn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tacticCounts
	{
		get
		{
			return 0;
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

	public List<ObscuredInt> tileId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int battleTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int baseOccupation
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxOccupation
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int productionType0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int productionValue0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int productionType1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int productionValue1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string npcName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int npcRank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int npcUnitLv
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
}
