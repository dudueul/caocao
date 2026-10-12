using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildSiegeSlot : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_slotNo;

	[SerializeField]
	private ObscuredInt m_slotType;

	[SerializeField]
	private List<ObscuredInt> m_linkedSlot;

	[SerializeField]
	private ObscuredInt m_defaultFacility;

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

	public int slotNo
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int slotType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> linkedSlot
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int defaultFacility
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
