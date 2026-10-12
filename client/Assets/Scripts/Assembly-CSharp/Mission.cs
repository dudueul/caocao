using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Mission : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_mission;

	[SerializeField]
	private ObscuredInt m_unitId01;

	[SerializeField]
	private ObscuredInt m_unitId02;

	[SerializeField]
	private ObscuredInt m_unitId03;

	[SerializeField]
	private ObscuredInt m_forceType;

	[SerializeField]
	private ObscuredInt m_jobType;

	[SerializeField]
	private ObscuredInt m_treasureType;

	[SerializeField]
	private ObscuredInt m_count;

	[SerializeField]
	private ObscuredInt m_limitTurn;

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

	public int mission
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitId01
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitId02
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitId03
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int forceType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int jobType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int treasureType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int count
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int limitTurn
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
