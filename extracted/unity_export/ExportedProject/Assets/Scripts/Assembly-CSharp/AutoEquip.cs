using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class AutoEquip : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_equipType;

	[SerializeField]
	private List<ObscuredInt> m_Lv;

	[SerializeField]
	private List<ObscuredInt> m_equip;

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

	public int equipType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> Lv
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> equip
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
