using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ScenarioFlag : PermanentBase
{
	[SerializeField]
	private ObscuredString m_flagName;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_storyBookId;

	[SerializeField]
	private ObscuredInt m_unitId;

	[SerializeField]
	private ObscuredInt m_startGuage;

	[SerializeField]
	private ObscuredString m_colorCode;

	public string flagName
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

	public int storyBookId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int startGuage
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string colorCode
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
