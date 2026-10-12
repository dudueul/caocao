using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RunePassiveExp : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private List<ObscuredInt> m_needEXP;

	[SerializeField]
	private List<ObscuredInt> m_expSum;

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

	public int tier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> needEXP
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> expSum
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
