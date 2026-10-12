using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildSupport : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_item;

	[SerializeField]
	private ObscuredInt m_openGrade;

	[SerializeField]
	private List<ObscuredInt> m_maxNum;

	[SerializeField]
	private ObscuredInt m_pointPerMeasure;

	[SerializeField]
	private ObscuredInt m_measure;

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

	public int item
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int openGrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> maxNum
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int pointPerMeasure
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int measure
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
