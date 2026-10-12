using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaGetUnit : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_jobType;

	[SerializeField]
	private List<ObscuredInt> m_unit;

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

	public List<ObscuredInt> unit
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
