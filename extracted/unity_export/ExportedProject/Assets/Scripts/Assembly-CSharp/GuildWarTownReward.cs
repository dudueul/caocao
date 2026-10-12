using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarTownReward : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_grade;

	[SerializeField]
	private ObscuredInt m_reward1;

	[SerializeField]
	private ObscuredInt m_reward2;

	[SerializeField]
	private ObscuredInt m_reward3;

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

	public int grade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward3
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
