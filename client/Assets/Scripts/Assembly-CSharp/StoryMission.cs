using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class StoryMission : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_storyID;

	[SerializeField]
	private ObscuredInt m_storyType;

	[SerializeField]
	private List<ObscuredInt> m_missionCount;

	[SerializeField]
	private List<ObscuredInt> m_rewardType;

	[SerializeField]
	private List<ObscuredInt> m_reward;

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

	public int storyID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int storyType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> missionCount
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> rewardType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> reward
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
