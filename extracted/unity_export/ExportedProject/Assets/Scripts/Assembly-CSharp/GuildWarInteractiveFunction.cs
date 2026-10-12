using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarInteractiveFunction : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_corpsExp;

	[SerializeField]
	private ObscuredInt m_contributionPoint;

	[SerializeField]
	private List<ObscuredInt> m_actionId;

	[SerializeField]
	private List<ObscuredInt> m_isFire;

	[SerializeField]
	private List<ObscuredInt> m_isWater;

	[SerializeField]
	private List<ObscuredInt> m_isEarth;

	[SerializeField]
	private List<ObscuredInt> m_isWind;

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

	public int corpsExp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int contributionPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> actionId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> isFire
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> isWater
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> isEarth
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> isWind
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
