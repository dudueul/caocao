using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class NPCTreasureSetting : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_npcSettingId;

	[SerializeField]
	private ObscuredInt m_itemType;

	[SerializeField]
	private ObscuredInt m_itemId;

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

	public int npcSettingId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemId
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
