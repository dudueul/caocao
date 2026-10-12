using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Mix : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_price;

	[SerializeField]
	private ObscuredInt m_gold;

	[SerializeField]
	private ObscuredInt m_isUse;

	[SerializeField]
	private List<ObscuredInt> m_mixId;

	[SerializeField]
	private List<ObscuredInt> m_cnt;

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

	public int price
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

	public int isUse
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> mixId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> cnt
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
