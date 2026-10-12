using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class WorldBossOffering : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_goodsType;

	[SerializeField]
	private ObscuredInt m_itemId;

	[SerializeField]
	private ObscuredInt m_count;

	[SerializeField]
	private List<ObscuredInt> m_seasonId;

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

	public int goodsType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int count
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> seasonId
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
