using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Reward : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_setTimeBox;

	[SerializeField]
	private ObscuredInt m_setGood;

	[SerializeField]
	private List<ObscuredInt> m_setItem;

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

	public int setTimeBox
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int setGood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> setItem
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
