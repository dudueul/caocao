using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RuneSet : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_unlock;

	[SerializeField]
	private List<ObscuredInt> m_type;

	[SerializeField]
	private List<ObscuredInt> m_mainStat;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private ObscuredInt m_minTier;

	[SerializeField]
	private ObscuredInt m_passiveListId;

	[SerializeField]
	private ObscuredString m_filterName;

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

	public int unlock
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> type
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> mainStat
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

	public int minTier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passiveListId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string filterName
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
