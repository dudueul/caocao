using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RecommendShop : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_order;

	[SerializeField]
	private ObscuredInt m_shopType;

	[SerializeField]
	private ObscuredInt m_productID;

	[SerializeField]
	private ObscuredInt m_saleRibbon;

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

	public int order
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int productID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int saleRibbon
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
