using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GameModeRule : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_pRawDmg;

	[SerializeField]
	private ObscuredInt m_sRawDmg;

	[SerializeField]
	private ObscuredInt m_randDmg;

	[SerializeField]
	private ObscuredInt m_multipleDmg;

	[SerializeField]
	private ObscuredInt m_multipleHeal;

	[SerializeField]
	private ObscuredInt m_multipleHPBase;

	[SerializeField]
	private ObscuredInt m_multipleHPByLv;

	[SerializeField]
	private ObscuredInt m_toughFightRange;

	[SerializeField]
	private ObscuredInt m_maxCost;

	[SerializeField]
	private ObscuredInt m_canPause;

	[SerializeField]
	private ObscuredInt m_canContinue;

	[SerializeField]
	private ObscuredInt m_canGetExp;

	[SerializeField]
	private ObscuredInt m_canTag;

	[SerializeField]
	private ObscuredInt m_isItemOn;

	[SerializeField]
	private ObscuredInt m_isUndoOn;

	[SerializeField]
	private ObscuredInt m_countUnit;

	[SerializeField]
	private ObscuredInt m_countHoldUnit;

	[SerializeField]
	private ObscuredInt m_countBanUnit;

	[SerializeField]
	private ObscuredInt m_initUserSpellpt;

	[SerializeField]
	private ObscuredInt m_maxUserSpellpt;

	[SerializeField]
	private ObscuredInt m_turnUserSpellPt;

	[SerializeField]
	private List<ObscuredString> m_rulePrefab;

	[SerializeField]
	private List<ObscuredInt> m_blackListPassive;

	[SerializeField]
	private List<ObscuredInt> m_fairTreasureEnhance;

	[SerializeField]
	private List<ObscuredInt> m_fairTreasureEnhancePassive;

	[SerializeField]
	private List<ObscuredInt> m_fairRune;

	[SerializeField]
	private List<ObscuredInt> m_fairRuneSet;

	[SerializeField]
	private List<ObscuredInt> m_fairRuneEnhance;

	[SerializeField]
	private List<ObscuredString> m_fairDesc;

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

	public int pRawDmg
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int sRawDmg
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int randDmg
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int multipleDmg
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int multipleHeal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int multipleHPBase
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int multipleHPByLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int toughFightRange
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxCost
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canPause
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canContinue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canGetExp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canTag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isItemOn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isUndoOn
	{
		get
		{
			return 0;
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

	public int countHoldUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int countBanUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int initUserSpellpt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxUserSpellpt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int turnUserSpellPt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredString> rulePrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> blackListPassive
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fairTreasureEnhance
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fairTreasureEnhancePassive
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fairRune
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fairRuneSet
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> fairRuneEnhance
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredString> fairDesc
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
