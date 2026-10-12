using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarCorpsJob : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_corpsPrefab1;

	[SerializeField]
	private ObscuredString m_corpsPrefab2;

	[SerializeField]
	private ObscuredString m_corpsPrefab3;

	[SerializeField]
	private ObscuredString m_shipPrefab;

	[SerializeField]
	private ObscuredString m_emblem;

	[SerializeField]
	private ObscuredInt m_corpsJobType;

	[SerializeField]
	private ObscuredInt m_canCreate;

	[SerializeField]
	private ObscuredInt m_isShowList;

	[SerializeField]
	private ObscuredInt m_orderNumber;

	[SerializeField]
	private ObscuredInt m_defType;

	[SerializeField]
	private ObscuredInt m_corpsJobRank;

	[SerializeField]
	private ObscuredInt m_maxCorpsLevel;

	[SerializeField]
	private ObscuredInt m_upgradeJob;

	[SerializeField]
	private ObscuredInt m_maxCorpsHp;

	[SerializeField]
	private ObscuredInt m_baseDmg;

	[SerializeField]
	private ObscuredInt m_baseAtk;

	[SerializeField]
	private ObscuredInt m_baseDef;

	[SerializeField]
	private ObscuredInt m_baseInte;

	[SerializeField]
	private ObscuredInt m_baseAgi;

	[SerializeField]
	private ObscuredInt m_baseMor;

	[SerializeField]
	private ObscuredInt m_corpsAtkRate;

	[SerializeField]
	private ObscuredInt m_corpsDefRate;

	[SerializeField]
	private ObscuredInt m_corpsInteRate;

	[SerializeField]
	private ObscuredInt m_corpsAgiRate;

	[SerializeField]
	private ObscuredInt m_corpsMorRate;

	[SerializeField]
	private ObscuredInt m_corpsAtkRatePlus;

	[SerializeField]
	private ObscuredInt m_corpsDefRatePlus;

	[SerializeField]
	private ObscuredInt m_corpsInteRatePlus;

	[SerializeField]
	private ObscuredInt m_corpsAgiRatePlus;

	[SerializeField]
	private ObscuredInt m_corpsMorRatePlus;

	[SerializeField]
	private ObscuredInt m_corpsMaxActionPoint;

	[SerializeField]
	private ObscuredInt m_actionPointRecoveryTime;

	[SerializeField]
	private ObscuredInt m_actionPointRecoveryAmount;

	[SerializeField]
	private ObscuredInt m_globalCoolTime;

	[SerializeField]
	private ObscuredInt m_moveCoolTime;

	[SerializeField]
	private ObscuredInt m_maxCorpsWoundedRate;

	[SerializeField]
	private ObscuredInt m_sight;

	[SerializeField]
	private ObscuredInt m_move;

	[SerializeField]
	private List<ObscuredInt> m_passiveListId;

	[SerializeField]
	private ObscuredInt m_canDismiss;

	[SerializeField]
	private ObscuredInt m_canMove;

	[SerializeField]
	private ObscuredInt m_canMountSiegeWeapon;

	[SerializeField]
	private ObscuredInt m_canDismountSiegeWeapon;

	[SerializeField]
	private ObscuredInt m_counterFunctionId;

	[SerializeField]
	private ObscuredInt m_counterMaxRange;

	[SerializeField]
	private ObscuredInt m_basicAttackFunctionId;

	[SerializeField]
	private List<ObscuredInt> m_functionId;

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

	public string corpsPrefab1
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string corpsPrefab2
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string corpsPrefab3
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string shipPrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string emblem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int corpsJobType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canCreate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isShowList
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int orderNumber
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int defType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsJobRank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxCorpsLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int upgradeJob
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxCorpsHp
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

	public int baseAtk
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int baseDef
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int baseInte
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int baseAgi
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int baseMor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsAtkRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsDefRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsInteRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsAgiRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsMorRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsAtkRatePlus
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsDefRatePlus
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsInteRatePlus
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsAgiRatePlus
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsMorRatePlus
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsMaxActionPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int actionPointRecoveryTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int actionPointRecoveryAmount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int globalCoolTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int moveCoolTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxCorpsWoundedRate
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

	public int move
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

	public int canDismiss
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canMove
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canMountSiegeWeapon
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canDismountSiegeWeapon
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

	public int basicAttackFunctionId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> functionId
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
