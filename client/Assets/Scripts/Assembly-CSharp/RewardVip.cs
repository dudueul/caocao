using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RewardVip : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_reward;

	[SerializeField]
	private List<ObscuredInt> m_vipLv;

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

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

	public List<ObscuredInt> vipLv
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
