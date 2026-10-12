using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Job : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_move;

	[SerializeField]
	private ObscuredInt m_targetArea;

	[SerializeField]
	private ObscuredInt m_effectArea;

	[SerializeField]
	private ObscuredInt m_isProjectile;

	[SerializeField]
	private ObscuredInt m_isCatapult;

	[SerializeField]
	private ObscuredInt m_hpPlus;

	[SerializeField]
	private ObscuredInt m_mpPlus;

	[SerializeField]
	private ObscuredInt m_skillFold;

	[SerializeField]
	private ObscuredInt m_projectileFold;

	[SerializeField]
	private ObscuredInt m_catapultFold;

	[SerializeField]
	private List<ObscuredInt> m_passive;

	[SerializeField]
	private List<ObscuredInt> m_passiveVal;

	[SerializeField]
	private ObscuredInt m_aiPriority;

	[SerializeField]
	private ObscuredInt m_aiRole;

	[SerializeField]
	private ObscuredInt m_aiType;

	[SerializeField]
	private ObscuredInt m_frontAdv;

	[SerializeField]
	private ObscuredInt m_middleAdv;

	[SerializeField]
	private ObscuredInt m_rearAdv;

	[SerializeField]
	private ObscuredInt m_hasTurn;

	[SerializeField]
	private ObscuredInt m_fireResist;

	[SerializeField]
	private List<ObscuredInt> m_itemType;

	[SerializeField]
	private ObscuredInt m_canPincers;

	[SerializeField]
	private ObscuredInt m_style;

	[SerializeField]
	private ObscuredString m_moveSE;

	[SerializeField]
	private ObscuredInt m_selectSE;

	[SerializeField]
	private ObscuredInt m_eMoveSE;

	[SerializeField]
	private ObscuredInt m_moveSpeed;

	[SerializeField]
	private ObscuredInt m_atkDelay;

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

	public int isProjectile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isCatapult
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int hpPlus
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mpPlus
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int skillFold
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int projectileFold
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int catapultFold
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

	public int aiPriority
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int aiRole
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int aiType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int frontAdv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int middleAdv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rearAdv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int hasTurn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int fireResist
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> itemType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int canPincers
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int style
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string moveSE
	{
		get
		{
			return null;
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

	public int eMoveSE
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int moveSpeed
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int atkDelay
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
