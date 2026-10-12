using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class AISet : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_hpAI1;

	[SerializeField]
	private ObscuredInt m_hpAI2;

	[SerializeField]
	private ObscuredInt m_mpAI;

	[SerializeField]
	private List<ObscuredInt> m_techLv;

	[SerializeField]
	private List<ObscuredInt> m_tendencyParts;

	[SerializeField]
	private List<ObscuredInt> m_spellParts;

	[SerializeField]
	private List<ObscuredInt> m_recoverTendency;

	[SerializeField]
	private List<ObscuredInt> m_recoverSpell;

	[SerializeField]
	private List<ObscuredInt> m_hpRecoverTendency;

	[SerializeField]
	private List<ObscuredInt> m_hpRecoverSpell;

	[SerializeField]
	private List<ObscuredInt> m_mpRecoverTendency;

	[SerializeField]
	private List<ObscuredInt> m_mpRecoverSpell;

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

	public int hpAI1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int hpAI2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mpAI
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> techLv
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> tendencyParts
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> spellParts
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> recoverTendency
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> recoverSpell
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> hpRecoverTendency
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> hpRecoverSpell
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> mpRecoverTendency
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> mpRecoverSpell
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
