using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class StaffRoll : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_title;

	[SerializeField]
	private ObscuredString m_position;

	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_country;

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

	public string title
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string position
	{
		get
		{
			return null;
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

	public int country
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
