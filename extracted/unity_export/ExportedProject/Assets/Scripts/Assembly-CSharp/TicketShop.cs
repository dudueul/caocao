using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TicketShop : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_item;

	[SerializeField]
	private ObscuredInt m_amount;

	[SerializeField]
	private ObscuredInt m_itemtype;

	[SerializeField]
	private ObscuredInt m_cost;

	[SerializeField]
	private ObscuredString m_slot;

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

	public int amount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemtype
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cost
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string slot
	{
		get
		{
			return null;
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
