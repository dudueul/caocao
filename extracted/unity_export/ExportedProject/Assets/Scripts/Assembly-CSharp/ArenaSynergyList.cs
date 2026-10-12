using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaSynergyList : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_prevSynergy;

	[SerializeField]
	private ObscuredInt m_nextSynergy;

	[SerializeField]
	private List<ObscuredInt> m_synergy;

	[SerializeField]
	private List<ObscuredInt> m_jobStyleTarget;

	[SerializeField]
	private List<ObscuredInt> m_synergyTarget;

	[SerializeField]
	private List<ObscuredInt> m_passive;

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

	public int prevSynergy
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int nextSynergy
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> synergy
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> jobStyleTarget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> synergyTarget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passive
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
