using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Tower : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_castleGrade;

	[SerializeField]
	private ObscuredInt m_castleLv;

	[SerializeField]
	private List<ObscuredInt> m_tower;

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

	public int castleGrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int castleLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> tower
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
