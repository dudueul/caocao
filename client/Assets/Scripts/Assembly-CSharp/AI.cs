using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class AI : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_aiRole;

	[SerializeField]
	private List<ObscuredInt> m_aiSet;

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

	public List<ObscuredInt> aiRole
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> aiSet
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
