using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class UnitRankSeason : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_startDate;

	[SerializeField]
	private ObscuredInt m_endDate;

	[SerializeField]
	private ObscuredString m_country;

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

	public int startDate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int endDate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string country
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
