using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaPassiveList : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_passive;

	[SerializeField]
	private ObscuredInt m_value;

	[SerializeField]
	private ObscuredInt m_triggerRateValue;

	[SerializeField]
	private List<ObscuredInt> m_conditionVal;

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

	public int value
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
}
