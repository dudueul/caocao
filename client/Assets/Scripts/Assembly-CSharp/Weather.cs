using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Weather : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredInt m_serenity;

	[SerializeField]
	private ObscuredInt m_cloud;

	[SerializeField]
	private ObscuredInt m_rain;

	[SerializeField]
	private ObscuredInt m_snow;

	[SerializeField]
	private ObscuredInt m_storm;

	[SerializeField]
	private ObscuredInt m_fog;

	[SerializeField]
	private ObscuredInt m_none;

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

	public int serenity
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cloud
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rain
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int snow
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int storm
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int fog
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int none
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
