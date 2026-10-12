using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaNPCPassive : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_passive;

	[SerializeField]
	private ObscuredInt m_value;

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

	public int type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int passive
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value
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
