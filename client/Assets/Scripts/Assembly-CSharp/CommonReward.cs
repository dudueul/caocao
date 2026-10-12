using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class CommonReward : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_point;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredString m_desc;

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

	public int point
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type0
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
