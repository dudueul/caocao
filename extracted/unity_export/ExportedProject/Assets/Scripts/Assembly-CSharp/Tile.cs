using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Tile : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_color;

	[SerializeField]
	private ObscuredInt m_canFire;

	[SerializeField]
	private ObscuredInt m_canIce;

	[SerializeField]
	private ObscuredInt m_canStone;

	[SerializeField]
	private ObscuredInt m_canWind;

	[SerializeField]
	private ObscuredInt m_hp;

	[SerializeField]
	private ObscuredInt m_mp;

	[SerializeField]
	private ObscuredInt m_clearBuff;

	[SerializeField]
	private ObscuredInt m_clearDebuff;

	[SerializeField]
	private ObscuredInt m_isIgnited;

	[SerializeField]
	private ObscuredInt m_ignitionRate;

	[SerializeField]
	private ObscuredString m_setCondition;

	[SerializeField]
	private ObscuredString m_effectRate;

	[SerializeField]
	private ObscuredInt m_mask;

	[SerializeField]
	private ObscuredInt m_waterDefense;

	[SerializeField]
	private ObscuredInt m_mountainDefense;

	[SerializeField]
	private ObscuredInt m_desertDefense;

	[SerializeField]
	private ObscuredInt m_waterSpecialization;

	[SerializeField]
	private ObscuredInt m_mountainSpecialization;

	[SerializeField]
	private ObscuredInt m_mountainHelp;

	[SerializeField]
	private ObscuredInt m_desertHelp;

	[SerializeField]
	private ObscuredInt m_castleHelp;

	[SerializeField]
	private ObscuredInt m_snowHelp;

	[SerializeField]
	private ObscuredInt m_riverHelp;

	[SerializeField]
	private ObscuredInt m_alwaysShadowEnabled;

	[SerializeField]
	private ObscuredInt m_tileScoreForAI;

	[SerializeField]
	private ObscuredString m_SE;

	[SerializeField]
	private ObscuredInt m_trail;

	[SerializeField]
	private ObscuredInt m_duelBGM;

	[SerializeField]
	private ObscuredInt m_duelMap;

	[SerializeField]
	private ObscuredString m_tileIcon;

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

	public string color
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int canFire
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canIce
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canStone
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canWind
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

	public int clearBuff
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int clearDebuff
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isIgnited
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int ignitionRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string setCondition
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string effectRate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int mask
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int waterDefense
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mountainDefense
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int desertDefense
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int waterSpecialization
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mountainSpecialization
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mountainHelp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int desertHelp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int castleHelp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int snowHelp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int riverHelp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int alwaysShadowEnabled
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tileScoreForAI
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string SE
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int trail
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int duelBGM
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int duelMap
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string tileIcon
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
