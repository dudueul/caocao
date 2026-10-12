using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ReentCost : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_rCount;

	[SerializeField]
	private ObscuredInt m_reentType;

	[SerializeField]
	private ObscuredInt m_reentCost0;

	[SerializeField]
	private ObscuredInt m_reentCost1;

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

	public int rCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reentType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reentCost0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reentCost1
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
