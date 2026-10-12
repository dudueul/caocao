using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class BattleRule : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_ruleTarget;

	[SerializeField]
	private List<ObscuredInt> m_passiveId;

	[SerializeField]
	private List<ObscuredInt> m_passiveVal;

	[SerializeField]
	private ObscuredInt m_isDisplay;

	[SerializeField]
	private ObscuredString m_desc;

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

	public int ruleTarget
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveVal
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int isDisplay
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
