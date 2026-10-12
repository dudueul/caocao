using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarCombatMessage : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_faceId;

	[SerializeField]
	private ObscuredString m_allyDesc;

	[SerializeField]
	private ObscuredString m_globalDesc;

	[SerializeField]
	private ObscuredInt m_coolTime;

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

	public int faceId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string allyDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string globalDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int coolTime
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
