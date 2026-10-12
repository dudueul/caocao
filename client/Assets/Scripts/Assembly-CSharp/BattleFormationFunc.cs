using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class BattleFormationFunc : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_lv;

	[SerializeField]
	private ObscuredInt m_formationId;

	[SerializeField]
	private List<ObscuredInt> m_goodType;

	[SerializeField]
	private List<ObscuredInt> m_cost;

	[SerializeField]
	private ObscuredInt m_techId;

	[SerializeField]
	private ObscuredInt m_needLv;

	[SerializeField]
	private List<ObscuredInt> m_effectType;

	[SerializeField]
	private List<ObscuredInt> m_effectVal;

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

	public int lv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int formationId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> goodType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> cost
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int techId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectVal
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
