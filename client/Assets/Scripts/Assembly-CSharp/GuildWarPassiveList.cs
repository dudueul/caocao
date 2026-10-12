using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarPassiveList : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_passiveAttributeId;

	[SerializeField]
	private ObscuredInt m_passiveValue;

	[SerializeField]
	private ObscuredInt m_durationTime;

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

	public int passiveAttributeId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passiveValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int durationTime
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
