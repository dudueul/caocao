using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class WeakPassive : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredString> m_weakAttackType;

	[SerializeField]
	private List<ObscuredString> m_weakOpenType;

	[SerializeField]
	private ObscuredInt m_conditionHarden;

	[SerializeField]
	private ObscuredInt m_conditionSoften;

	[SerializeField]
	private ObscuredInt m_maxArmor;

	[SerializeField]
	private ObscuredInt m_addArmor;

	[SerializeField]
	private ObscuredInt m_weakDamageRate;

	[SerializeField]
	private ObscuredInt m_strongDamageRate;

	[SerializeField]
	private ObscuredInt m_softenDuration;

	[SerializeField]
	private ObscuredInt m_softenDamageRate;

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

	public List<ObscuredString> weakAttackType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredString> weakOpenType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int conditionHarden
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int conditionSoften
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxArmor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int addArmor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weakDamageRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int strongDamageRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int softenDuration
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int softenDamageRate
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
