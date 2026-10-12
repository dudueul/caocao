using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class BrawlRotation : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_isAllWhite;

	[SerializeField]
	private List<ObscuredInt> m_whiteList;

	[SerializeField]
	private ObscuredInt m_nextId;

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

	public int isAllWhite
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> whiteList
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int nextId
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
