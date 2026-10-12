using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class EventReward : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_mapId;

	[SerializeField]
	private ObscuredInt m_rewardType;

	[SerializeField]
	private ObscuredInt m_rewardId;

	[SerializeField]
	private ObscuredInt m_saveFlag;

	[SerializeField]
	private ObscuredInt m_count;

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

	public int mapId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int saveFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int count
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
