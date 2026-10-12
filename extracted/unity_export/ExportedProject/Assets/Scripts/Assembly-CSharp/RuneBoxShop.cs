using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RuneBoxShop : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_boxType;

	[SerializeField]
	private ObscuredString m_perDesc;

	[SerializeField]
	private ObscuredInt m_goodType;

	[SerializeField]
	private ObscuredInt m_cost;

	[SerializeField]
	private ObscuredInt m_needItemId;

	[SerializeField]
	private ObscuredInt m_needItemCount;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_boxEffect;

	[SerializeField]
	private ObscuredInt m_getPlace;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredString m_bonusDesc;

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

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

	public int boxType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string perDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int goodType
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

	public int needItemId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needItemCount
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

	public string boxEffect
	{
		get
		{
			return null;
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

	public string bonusDesc
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
