using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SoldierSlot : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_value;

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
}
