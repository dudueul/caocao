using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class VipFeature : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredString m_alert;

	[SerializeField]
	private ObscuredInt m_descCountry;

	[SerializeField]
	private List<ObscuredInt> m_vipLV;

	[SerializeField]
	private List<ObscuredInt> m_val;

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

	public string alert
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int descCountry
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> vipLV
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> val
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
