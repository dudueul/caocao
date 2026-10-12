using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class CharType : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredString> m_criticalMsg;

	[SerializeField]
	private ObscuredInt m_criticalRate;

	[SerializeField]
	private ObscuredInt m_autoRebirth;

	[SerializeField]
	private ObscuredInt m_deathcounter;

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

	public List<ObscuredString> criticalMsg
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int criticalRate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int autoRebirth
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int deathcounter
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
