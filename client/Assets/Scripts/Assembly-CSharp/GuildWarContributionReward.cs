using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarContributionReward : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_minContribution;

	[SerializeField]
	private ObscuredInt m_maxContribution;

	[SerializeField]
	private ObscuredInt m_reward0;

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

	public int minContribution
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int maxContribution
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward0
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
