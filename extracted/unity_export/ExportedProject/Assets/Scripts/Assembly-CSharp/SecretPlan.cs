using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SecretPlan : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_skillId;

	[SerializeField]
	private ObscuredInt m_runArea;

	[SerializeField]
	private ObscuredInt m_hp;

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

	public int skillId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int runArea
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int hp
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
