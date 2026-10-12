using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class CraftTreasurePassive : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_enhanceType;

	[SerializeField]
	private ObscuredInt m_passiveListId;

	[SerializeField]
	private ObscuredInt m_rate;

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

	public int enhanceType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passiveListId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rate
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
