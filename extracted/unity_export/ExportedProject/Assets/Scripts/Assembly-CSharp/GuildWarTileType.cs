using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarTileType : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_canFire;

	[SerializeField]
	private ObscuredInt m_canWater;

	[SerializeField]
	private ObscuredInt m_canEarth;

	[SerializeField]
	private ObscuredInt m_canWind;

	[SerializeField]
	private ObscuredInt m_canSetSiegeWeapon;

	[SerializeField]
	private ObscuredInt m_canRush;

	[SerializeField]
	private ObscuredInt m_canWaterRush;

	[SerializeField]
	private List<ObscuredInt> m_tileEffect;

	[SerializeField]
	private ObscuredInt m_isWater;

	[SerializeField]
	private ObscuredInt m_isIgnited;

	[SerializeField]
	private ObscuredInt m_waterDefense;

	[SerializeField]
	private ObscuredInt m_mountainDefense;

	[SerializeField]
	private ObscuredInt m_desertDefense;

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

	public int canFire
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canWater
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canEarth
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canWind
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canSetSiegeWeapon
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canRush
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int canWaterRush
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> tileEffect
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int isWater
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isIgnited
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int waterDefense
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mountainDefense
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int desertDefense
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
