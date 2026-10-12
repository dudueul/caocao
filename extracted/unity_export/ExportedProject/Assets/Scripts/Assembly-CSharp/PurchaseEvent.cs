using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class PurchaseEvent : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_title;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

	[SerializeField]
	private ObscuredString m_currencyCode;

	[SerializeField]
	private ObscuredInt m_totalPrice;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private List<ObscuredString> m_slotName;

	[SerializeField]
	private List<ObscuredInt> m_itemId;

	[SerializeField]
	private List<ObscuredInt> m_price;

	[SerializeField]
	private List<ObscuredString> m_priceString;

	[SerializeField]
	private List<ObscuredInt> m_cashShopID;

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

	public string title
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string desc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string startDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string endDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string currencyCode
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int totalPrice
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

	public List<ObscuredString> slotName
	{
		get
		{
			return null;
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

	public List<ObscuredInt> price
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredString> priceString
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> cashShopID
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
