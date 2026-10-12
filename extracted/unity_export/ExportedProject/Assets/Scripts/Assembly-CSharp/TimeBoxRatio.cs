using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TimeBoxRatio : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_boxId;

	[SerializeField]
	private ObscuredInt m_itemId;

	[SerializeField]
	private ObscuredInt m_goodsType;

	[SerializeField]
	private ObscuredInt m_treasureType;

	[SerializeField]
	private ObscuredInt m_slot;

	[SerializeField]
	private ObscuredInt m_itemtier;

	[SerializeField]
	private List<ObscuredInt> m_min;

	[SerializeField]
	private List<ObscuredInt> m_max;

	[SerializeField]
	private List<ObscuredInt> m_itemRatio;

	[SerializeField]
	private ObscuredInt m_cardTier;

	[SerializeField]
	private ObscuredInt m_multiple;

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

	public int boxId
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

	public List<ObscuredInt> min
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> max
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> itemRatio
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int cardTier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int multiple
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
