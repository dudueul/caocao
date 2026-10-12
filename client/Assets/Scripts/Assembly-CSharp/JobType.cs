using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class JobType : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_battlePrefab;

	[SerializeField]
	private ObscuredInt m_maxAtk;

	[SerializeField]
	private ObscuredInt m_maxDef;

	[SerializeField]
	private ObscuredInt m_maxInt;

	[SerializeField]
	private ObscuredInt m_maxAgi;

	[SerializeField]
	private ObscuredInt m_maxMor;

	[SerializeField]
	private ObscuredInt m_maxHp;

	[SerializeField]
	private ObscuredInt m_maxMp;

	[SerializeField]
	private ObscuredInt m_dmgToObj;

	[SerializeField]
	private ObscuredInt m_resourceType;

	[SerializeField]
	private ObscuredInt m_atkLv;

	[SerializeField]
	private ObscuredInt m_defLv;

	[SerializeField]
	private ObscuredInt m_intLv;

	[SerializeField]
	private ObscuredInt m_agiLv;

	[SerializeField]
	private ObscuredInt m_morLv;

	[SerializeField]
	private ObscuredInt m_hpLv;

	[SerializeField]
	private ObscuredInt m_mpLv;

	[SerializeField]
	private ObscuredInt m_deckType;

	[SerializeField]
	private List<ObscuredInt> m_job;

	[SerializeField]
	private List<ObscuredInt> m_lv;

	[SerializeField]
	private ObscuredInt m_autoWeaponId;

	[SerializeField]
	private ObscuredInt m_autoArmorId;

	[SerializeField]
	private ObscuredInt m_autoAccId;

	[SerializeField]
	private ObscuredInt m_fShadow;

	[SerializeField]
	private ObscuredInt m_lShadow;

	[SerializeField]
	private ObscuredInt m_trailType;

	[SerializeField]
	private ObscuredInt m_fxOffset;

	[SerializeField]
	private ObscuredInt m_tileInfo;

	[SerializeField]
	private ObscuredInt m_npcCheck;

	[SerializeField]
	private ObscuredInt m_supporter;

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

	public string battlePrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int maxAtk
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxDef
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxInt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxAgi
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxMor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxHp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxMp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int dmgToObj
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int resourceType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int atkLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int defLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int intLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int agiLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int morLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int hpLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mpLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int deckType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> job
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> lv
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int autoWeaponId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int autoArmorId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int autoAccId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int fShadow
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int lShadow
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int trailType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int fxOffset
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tileInfo
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int npcCheck
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int supporter
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
