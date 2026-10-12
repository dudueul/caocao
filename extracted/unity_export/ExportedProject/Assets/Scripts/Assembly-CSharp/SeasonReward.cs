using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SeasonReward : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_seasonId;

	[SerializeField]
	private ObscuredInt m_treasureType;

	[SerializeField]
	private ObscuredInt m_slot;

	[SerializeField]
	private ObscuredInt m_itemtier;

	[SerializeField]
	private ObscuredInt m_replaceitemId;

	[SerializeField]
	private ObscuredInt m_replaceitemCount;

	[SerializeField]
	private List<ObscuredInt> m_itemId;

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

	public int seasonId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int treasureType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int slot
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemtier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int replaceitemId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int replaceitemCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> itemId
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
