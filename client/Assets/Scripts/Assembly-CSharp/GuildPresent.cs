using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildPresent : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_item;

	[SerializeField]
	private List<ObscuredInt> m_contribution;

	[SerializeField]
	private List<ObscuredInt> m_maxLimit;

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

	public int item
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> contribution
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> maxLimit
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
