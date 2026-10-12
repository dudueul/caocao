using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaStageGroup : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_group;

	[SerializeField]
	private ObscuredInt m_level;

	[SerializeField]
	private List<ObscuredInt> m_stage;

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

	public int group
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> stage
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
