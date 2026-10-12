using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildSiegeNPCEquipSetting : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_weaponTreasure;

	[SerializeField]
	private ObscuredInt m_weaponLv;

	[SerializeField]
	private ObscuredInt m_armorTreasure;

	[SerializeField]
	private ObscuredInt m_armorLv;

	[SerializeField]
	private ObscuredInt m_kitTreasure;

	[SerializeField]
	private ObscuredInt m_kitLv;

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

	public int weaponTreasure
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int weaponLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armorTreasure
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armorLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kitTreasure
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int kitLv
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
