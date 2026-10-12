using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaDialog : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_scenario;

	[SerializeField]
	private ObscuredInt m_theme;

	[SerializeField]
	private ObscuredInt m_level;

	[SerializeField]
	private ObscuredInt m_needAllUnit;

	[SerializeField]
	private ObscuredInt m_speechType;

	[SerializeField]
	private List<ObscuredInt> m_speechUnit;

	[SerializeField]
	private List<ObscuredInt> m_speechWindow;

	[SerializeField]
	private List<ObscuredString> m_speech;

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

	public int scenario
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int theme
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needAllUnit
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int speechType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> speechUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> speechWindow
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredString> speech
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
