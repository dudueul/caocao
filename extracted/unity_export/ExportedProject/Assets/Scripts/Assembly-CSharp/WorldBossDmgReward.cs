using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class WorldBossDmgReward : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_bossId;

	[SerializeField]
	private ObscuredInt m_needDamage;

	[SerializeField]
	private ObscuredInt m_rewardGoods;

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

	public int bossId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needDamage
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardGoods
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
