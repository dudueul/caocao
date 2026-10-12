using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TreasureSale : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private ObscuredInt m_isSale;

	[SerializeField]
	private ObscuredInt m_saleItemId;

	[SerializeField]
	private ObscuredInt m_saleCost;

	[SerializeField]
	private ObscuredInt m_country;

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

	public int isSale
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int saleItemId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int saleCost
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int country
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
