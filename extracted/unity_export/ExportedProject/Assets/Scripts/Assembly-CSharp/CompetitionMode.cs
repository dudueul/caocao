using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class CompetitionMode : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_countUnit;

	[SerializeField]
	private ObscuredInt m_countHoldUnit;

	[SerializeField]
	private ObscuredInt m_countBanUnit;

	[SerializeField]
	private ObscuredInt m_countQueueUnit;

	[SerializeField]
	private List<ObscuredInt> m_battleMap;

	[SerializeField]
	private ObscuredInt m_ranking;

	[SerializeField]
	private ObscuredString m_description;

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

	public int countUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int countHoldUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int countBanUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int countQueueUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> battleMap
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int ranking
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string description
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
