using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildSiegeObjectByLV : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_objectType;

	[SerializeField]
	private ObscuredInt m_targetUnit;

	[SerializeField]
	private ObscuredInt m_unitLv;

	[SerializeField]
	private ObscuredInt m_addStr;

	[SerializeField]
	private ObscuredInt m_addCha;

	[SerializeField]
	private ObscuredInt m_addWis;

	[SerializeField]
	private ObscuredInt m_addDex;

	[SerializeField]
	private ObscuredInt m_addLuck;

	[SerializeField]
	private List<ObscuredInt> m_passiveList;

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

	public int objectType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int targetUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int addStr
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int addCha
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int addWis
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int addDex
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int addLuck
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveList
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
