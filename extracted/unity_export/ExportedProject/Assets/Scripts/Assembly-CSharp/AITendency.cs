using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class AITendency : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_standBy;

	[SerializeField]
	private ObscuredInt m_cDistance;

	[SerializeField]
	private ObscuredInt m_cGoal;

	[SerializeField]
	private ObscuredInt m_cTarget;

	[SerializeField]
	private ObscuredInt m_cTurn;

	[SerializeField]
	private ObscuredInt m_cMove;

	[SerializeField]
	private ObscuredInt m_cRand;

	[SerializeField]
	private ObscuredInt m_cMinAtk;

	[SerializeField]
	private ObscuredInt m_cAtk;

	[SerializeField]
	private ObscuredInt m_cDefeat;

	[SerializeField]
	private ObscuredInt m_cMultiAtk;

	[SerializeField]
	private ObscuredInt m_cMaxMulti;

	[SerializeField]
	private ObscuredInt m_cSp;

	[SerializeField]
	private ObscuredInt m_cSpAccu;

	[SerializeField]
	private ObscuredInt m_cMultiSp;

	[SerializeField]
	private ObscuredInt m_cMp;

	[SerializeField]
	private ObscuredInt m_cMaxMp;

	[SerializeField]
	private ObscuredInt m_cDef;

	[SerializeField]
	private ObscuredInt m_cRetire;

	[SerializeField]
	private ObscuredInt m_cDst;

	[SerializeField]
	private ObscuredInt m_cThreat;

	[SerializeField]
	private ObscuredInt m_cThreatStart;

	[SerializeField]
	private ObscuredInt m_cOwn;

	[SerializeField]
	private ObscuredInt m_cOwnStart;

	[SerializeField]
	private ObscuredInt m_cExtension;

	[SerializeField]
	private ObscuredInt m_cExtensionStart;

	[SerializeField]
	private ObscuredInt m_cMoveCostF;

	[SerializeField]
	private ObscuredInt m_cTile;

	[SerializeField]
	private ObscuredInt m_cRecoverTile;

	[SerializeField]
	private ObscuredInt m_cHpRecoverTile;

	[SerializeField]
	private ObscuredInt m_cMpRecoverTile;

	[SerializeField]
	private ObscuredInt m_cConditionTile;

	[SerializeField]
	private ObscuredInt m_cHeal;

	[SerializeField]
	private ObscuredInt m_cMinHeal;

	[SerializeField]
	private ObscuredInt m_cMultiHeal;

	[SerializeField]
	private ObscuredInt m_cHealValid;

	[SerializeField]
	private ObscuredInt m_cPrice1;

	[SerializeField]
	private ObscuredInt m_cPrice2;

	[SerializeField]
	private ObscuredInt m_cItem;

	[SerializeField]
	private ObscuredInt m_cRecoverAd;

	[SerializeField]
	private ObscuredInt m_cTileScore;

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

	public int standBy
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cDistance
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cGoal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cTarget
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cTurn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMove
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRand
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMinAtk
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cAtk
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cDefeat
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMultiAtk
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMaxMulti
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cSp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cSpAccu
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMultiSp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMaxMp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cDef
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRetire
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cDst
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cThreat
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cThreatStart
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cOwn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cOwnStart
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cExtension
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cExtensionStart
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMoveCostF
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cTile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecoverTile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cHpRecoverTile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMpRecoverTile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cConditionTile
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cHeal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMinHeal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cMultiHeal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cHealValid
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cPrice1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cPrice2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cItem
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecoverAd
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cTileScore
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
