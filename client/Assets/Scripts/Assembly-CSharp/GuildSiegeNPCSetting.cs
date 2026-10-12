using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildSiegeNPCSetting : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_unit;

	[SerializeField]
	private List<ObscuredInt> m_equip;

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

	public List<ObscuredInt> unit
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
