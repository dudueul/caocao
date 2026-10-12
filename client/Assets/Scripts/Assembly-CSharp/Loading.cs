using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Loading : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_entry;

	[SerializeField]
	private ObscuredInt m_rate;

	[SerializeField]
	private ObscuredString m_matching;

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

	public int entry
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

	public string matching
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
