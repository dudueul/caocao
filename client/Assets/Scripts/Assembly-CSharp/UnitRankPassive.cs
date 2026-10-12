using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class UnitRankPassive : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_jobType;

	[SerializeField]
	private ObscuredInt m_rank;

	[SerializeField]
	private List<ObscuredInt> m_passive;

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

	public int jobType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passive
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
