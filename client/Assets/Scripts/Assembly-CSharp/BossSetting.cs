using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class BossSetting : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_unitid;

	[SerializeField]
	private List<ObscuredInt> m_weapon;

	[SerializeField]
	private List<ObscuredInt> m_armor;

	[SerializeField]
	private List<ObscuredInt> m_kit;

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

	public int type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitid
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> weapon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> armor
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> kit
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
