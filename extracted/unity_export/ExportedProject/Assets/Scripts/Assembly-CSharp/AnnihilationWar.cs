using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class AnnihilationWar : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_BattlemapId;

	[SerializeField]
	private ObscuredString m_battleMapImage;

	[SerializeField]
	private ObscuredInt m_unitCount;

	[SerializeField]
	private ObscuredInt m_tileId1;

	[SerializeField]
	private ObscuredInt m_tileId2;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredInt m_type1;

	[SerializeField]
	private ObscuredInt m_reward1;

	[SerializeField]
	private ObscuredString m_description;

	[SerializeField]
	private List<ObscuredInt> m_jobtype;

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

	public int BattlemapId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string battleMapImage
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int unitCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tileId1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tileId2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward1
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

	public List<ObscuredInt> jobtype
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
