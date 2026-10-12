using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class CraftTreasure : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_baseTreasure;

	[SerializeField]
	private ObscuredInt m_recipe;

	[SerializeField]
	private ObscuredInt m_craftStoneCount;

	[SerializeField]
	private ObscuredInt m_passiveStoneCount;

	[SerializeField]
	private ObscuredInt m_resultTreasure;

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

	public int baseTreasure
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int recipe
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int craftStoneCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passiveStoneCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int resultTreasure
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
