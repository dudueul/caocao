using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class League : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_leagueType;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredInt m_minRank;

	[SerializeField]
	private ObscuredInt m_maxRank;

	[SerializeField]
	private ObscuredInt m_mmrType;

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

	public int leagueType
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

	public int minRank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxRank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mmrType
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
