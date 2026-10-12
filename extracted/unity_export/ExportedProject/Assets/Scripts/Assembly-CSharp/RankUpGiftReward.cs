using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RankUpGiftReward : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_rankupgiftid;

	[SerializeField]
	private ObscuredInt m_country;

	[SerializeField]
	private List<ObscuredInt> m_lordRank;

	[SerializeField]
	private List<ObscuredInt> m_rewardType;

	[SerializeField]
	private List<ObscuredInt> m_reward;

	[SerializeField]
	private List<ObscuredString> m_atlas;

	[SerializeField]
	private List<ObscuredString> m_texture;

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

	public int rankupgiftid
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int country
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> lordRank
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

	public List<ObscuredString> atlas
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredString> texture
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
