using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildSiegeTownFacilityByLV : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_facilityLv;

	[SerializeField]
	private ObscuredInt m_needGuildLv;

	[SerializeField]
	private ObscuredInt m_expNeeded;

	[SerializeField]
	private ObscuredInt m_expSum;

	[SerializeField]
	private List<ObscuredInt> m_tower;

	[SerializeField]
	private List<ObscuredInt> m_obstacle;

	[SerializeField]
	private List<ObscuredInt> m_spawner;

	[SerializeField]
	private List<ObscuredInt> m_spawnerSet;

	[SerializeField]
	private ObscuredInt m_spcObject;

	[SerializeField]
	private ObscuredInt m_spcSpawnerSet;

	[SerializeField]
	private ObscuredInt m_commander;

	[SerializeField]
	private List<ObscuredInt> m_battleRule;

	[SerializeField]
	private List<ObscuredInt> m_defenderNPC;

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

	public int facilityLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needGuildLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int expNeeded
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int expSum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> tower
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> obstacle
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> spawner
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> spawnerSet
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int spcObject
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int spcSpawnerSet
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int commander
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> battleRule
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> defenderNPC
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
