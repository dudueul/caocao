using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class EventMap : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_mapTitle;

	[SerializeField]
	private ObscuredString m_prefab;

	[SerializeField]
	private ObscuredInt m_isFlip;

	[SerializeField]
	private ObscuredInt m_weather;

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

	public string mapTitle
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string prefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int isFlip
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weather
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
