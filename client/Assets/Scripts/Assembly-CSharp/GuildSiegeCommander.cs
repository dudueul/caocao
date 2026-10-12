using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildSiegeCommander : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_target;

	[SerializeField]
	private List<ObscuredInt> m_passiveId;

	[SerializeField]
	private List<ObscuredInt> m_passiveVal;

	[SerializeField]
	private ObscuredString m_desc;

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

	public List<ObscuredInt> target
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string desc
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
