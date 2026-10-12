using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ReturnSoul : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private ObscuredInt m_returnItemId;

	[SerializeField]
	private List<ObscuredInt> m_returnExp;

	[SerializeField]
	private List<ObscuredInt> m_needTicket;

	[SerializeField]
	private List<ObscuredInt> m_needItem;

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

	public int tier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int returnItemId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> returnExp
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> needTicket
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> needItem
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
