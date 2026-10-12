using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarPassive : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_passiveMarkerType;

	[SerializeField]
	private ObscuredInt m_passiveType;

	[SerializeField]
	private ObscuredInt m_damageType;

	[SerializeField]
	private ObscuredInt m_applyType;

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

	public int passiveMarkerType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passiveType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int damageType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int applyType
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
