using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarPlace : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_placeType;

	[SerializeField]
	private ObscuredInt m_spawnPoint;

	[SerializeField]
	private ObscuredInt m_linkedTownPlace;

	[SerializeField]
	private ObscuredInt m_changeOwn;

	[SerializeField]
	private List<ObscuredInt> m_buliding;

	[SerializeField]
	private List<ObscuredInt> m_ratio;

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

	public int placeType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spawnPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int linkedTownPlace
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int changeOwn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> buliding
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> ratio
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
