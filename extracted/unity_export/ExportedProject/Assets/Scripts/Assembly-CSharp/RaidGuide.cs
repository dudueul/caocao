using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RaidGuide : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_raidType;

	[SerializeField]
	private ObscuredInt m_unitId;

	[SerializeField]
	private List<ObscuredInt> m_icon;

	[SerializeField]
	private List<ObscuredString> m_description;

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

	public int raidType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> icon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredString> description
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
