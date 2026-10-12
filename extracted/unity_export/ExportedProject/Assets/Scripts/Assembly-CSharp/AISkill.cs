using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class AISkill : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_spAdd;

	[SerializeField]
	private ObscuredInt m_spWeather;

	[SerializeField]
	private ObscuredInt m_spElement;

	[SerializeField]
	private ObscuredInt m_spStorm;

	[SerializeField]
	private ObscuredInt m_spDrainHP;

	[SerializeField]
	private ObscuredInt m_spDrainMP;

	[SerializeField]
	private ObscuredInt m_spOneShot;

	[SerializeField]
	private ObscuredInt m_spAgiDn;

	[SerializeField]
	private ObscuredInt m_spMorDn;

	[SerializeField]
	private ObscuredInt m_spAtkDn;

	[SerializeField]
	private ObscuredInt m_spDefDn;

	[SerializeField]
	private ObscuredInt m_spAllUp;

	[SerializeField]
	private ObscuredInt m_spMovUp;

	[SerializeField]
	private ObscuredInt m_spAgiUp;

	[SerializeField]
	private ObscuredInt m_spMorUp;

	[SerializeField]
	private ObscuredInt m_spAtkUp;

	[SerializeField]
	private ObscuredInt m_spDefUp;

	[SerializeField]
	private ObscuredInt m_spConfusion;

	[SerializeField]
	private ObscuredInt m_spPoison;

	[SerializeField]
	private ObscuredInt m_spNoMov;

	[SerializeField]
	private ObscuredInt m_spNoSkill;

	[SerializeField]
	private ObscuredInt m_spHealMP;

	[SerializeField]
	private ObscuredInt m_spMeditation;

	[SerializeField]
	private ObscuredInt m_spTwoAct;

	[SerializeField]
	private ObscuredInt m_spGod1;

	[SerializeField]
	private ObscuredInt m_spGod2;

	[SerializeField]
	private ObscuredInt m_spGod3;

	[SerializeField]
	private ObscuredInt m_spGod4;

	[SerializeField]
	private ObscuredInt m_cRecover1;

	[SerializeField]
	private ObscuredInt m_cRecover2;

	[SerializeField]
	private ObscuredInt m_cRecover3;

	[SerializeField]
	private ObscuredInt m_cRecover4;

	[SerializeField]
	private ObscuredInt m_cRecover5;

	[SerializeField]
	private ObscuredInt m_cRecover6;

	[SerializeField]
	private ObscuredInt m_cRecover7;

	[SerializeField]
	private ObscuredInt m_cRecover8;

	[SerializeField]
	private ObscuredInt m_cRecover;

	[SerializeField]
	private ObscuredInt m_spEscort;

	[SerializeField]
	private ObscuredInt m_spBerserk;

	[SerializeField]
	private ObscuredInt m_spPhysic;

	[SerializeField]
	private ObscuredInt m_spNoAtk;

	[SerializeField]
	private ObscuredInt m_spNoItem;

	[SerializeField]
	private ObscuredInt m_spAllDn;

	[SerializeField]
	private ObscuredInt m_spMovDn;

	[SerializeField]
	private ObscuredInt m_spCurse;

	[SerializeField]
	private ObscuredInt m_spIgnSpr;

	[SerializeField]
	private ObscuredInt m_spBlind;

	[SerializeField]
	private ObscuredInt m_spAgiDn2;

	[SerializeField]
	private ObscuredInt m_spMorDn2;

	[SerializeField]
	private ObscuredInt m_spAtkDn2;

	[SerializeField]
	private ObscuredInt m_spDefDn2;

	[SerializeField]
	private ObscuredInt m_spMovUp2;

	[SerializeField]
	private ObscuredInt m_spAgiUp2;

	[SerializeField]
	private ObscuredInt m_spMorUp2;

	[SerializeField]
	private ObscuredInt m_spAtkUp2;

	[SerializeField]
	private ObscuredInt m_spDefUp2;

	[SerializeField]
	private ObscuredInt m_spSong;

	[SerializeField]
	private ObscuredInt m_spBD1;

	[SerializeField]
	private ObscuredInt m_spBD2;

	[SerializeField]
	private ObscuredInt m_spBD3;

	[SerializeField]
	private ObscuredInt m_spBD4;

	[SerializeField]
	private ObscuredInt m_spBD5;

	[SerializeField]
	private ObscuredInt m_spBD1R;

	[SerializeField]
	private ObscuredInt m_spBD2R;

	[SerializeField]
	private ObscuredInt m_spBD3R;

	[SerializeField]
	private ObscuredInt m_spBD4R;

	[SerializeField]
	private ObscuredInt m_spBD5R;

	[SerializeField]
	private ObscuredInt m_spBDRC;

	[SerializeField]
	private ObscuredInt m_spThunder;

	[SerializeField]
	private ObscuredInt m_cRVSoaring;

	[SerializeField]
	private ObscuredInt m_cRVBlooming;

	[SerializeField]
	private ObscuredInt m_cRVQuick;

	[SerializeField]
	private ObscuredInt m_cSpehrePushing;

	[SerializeField]
	private ObscuredInt m_cSpehrePulling;

	[SerializeField]
	private ObscuredInt m_cRVSpawn;

	[SerializeField]
	private ObscuredInt m_cRVTeleport;

	[SerializeField]
	private ObscuredInt m_cRVSlash;

	[SerializeField]
	private ObscuredInt m_spVan;

	[SerializeField]
	private ObscuredInt m_spVan2;

	[SerializeField]
	private ObscuredInt m_spCommand1;

	[SerializeField]
	private ObscuredInt m_spCommand2;

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

	public int spAdd
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spWeather
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spElement
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spStorm
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spDrainHP
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spDrainMP
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spOneShot
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAgiDn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spMorDn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAtkDn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spDefDn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAllUp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spMovUp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAgiUp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spMorUp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAtkUp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spDefUp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spConfusion
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spPoison
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spNoMov
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spNoSkill
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spHealMP
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spMeditation
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spTwoAct
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spGod1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spGod2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spGod3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spGod4
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover4
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover5
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover6
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover7
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover8
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRecover
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spEscort
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBerserk
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spPhysic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spNoAtk
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spNoItem
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAllDn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spMovDn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spCurse
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spIgnSpr
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBlind
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAgiDn2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spMorDn2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAtkDn2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spDefDn2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spMovUp2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAgiUp2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spMorUp2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spAtkUp2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spDefUp2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spSong
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD4
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD5
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD1R
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD2R
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD3R
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD4R
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBD5R
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spBDRC
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spThunder
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRVSoaring
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRVBlooming
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRVQuick
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cSpehrePushing
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cSpehrePulling
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRVSpawn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRVTeleport
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cRVSlash
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spVan
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spVan2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spCommand1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spCommand2
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
