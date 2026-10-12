using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class PassiveList : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_passive;

	[SerializeField]
	private ObscuredInt m_val;

	[SerializeField]
	private ObscuredInt m_triggerRateValue;

	[SerializeField]
	private List<ObscuredInt> m_conditionVal;

	[SerializeField]
	private ObscuredInt m_jobLv;

	[SerializeField]
	private ObscuredInt m_lv;

	[SerializeField]
	private ObscuredInt m_upgrade;

	[SerializeField]
	private ObscuredInt m_silver;

	[SerializeField]
	private ObscuredInt m_gold;

	[SerializeField]
	private ObscuredInt m_point;

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

	public int passive
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int val
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int triggerRateValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> conditionVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int jobLv
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

	public int upgrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silver
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int gold
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int point
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
