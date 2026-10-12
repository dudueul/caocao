using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ExchangeShop : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_slotType;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredString m_Title;

	[SerializeField]
	private ObscuredInt m_openItemID;

	[SerializeField]
	private ObscuredString m_startDate;

	[SerializeField]
	private ObscuredString m_endDate;

	[SerializeField]
	private ObscuredInt m_dateHidden;

	[SerializeField]
	private ObscuredInt m_alarmRibbon;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredInt m_tradeItemID;

	[SerializeField]
	private ObscuredInt m_getPlace;

	[SerializeField]
	private ObscuredInt m_shortcutId1;

	[SerializeField]
	private ObscuredInt m_shortcutId2;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredInt m_mailId;

	[SerializeField]
	private List<ObscuredInt> m_itemId;

	[SerializeField]
	private List<ObscuredInt> m_goodsType;

	[SerializeField]
	private List<ObscuredInt> m_amount;

	[SerializeField]
	private List<ObscuredInt> m_cost;

	[SerializeField]
	private List<ObscuredInt> m_maxNum;

	[SerializeField]
	private List<ObscuredInt> m_storage;

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

	public int slotType
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

	public string Title
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int openItemID
	{
		get
		{
			return 0;
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

	public int dateHidden
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int alarmRibbon
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string icon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int tradeItemID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getPlace
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shortcutId1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shortcutId2
	{
		get
		{
			return 0;
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

	public int mailId
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

	public List<ObscuredInt> goodsType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> amount
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> cost
	{
		get
		{
			return null;
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

	public List<ObscuredInt> storage
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
