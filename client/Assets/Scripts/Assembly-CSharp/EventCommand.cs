using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class EventCommand : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_Code;

	[SerializeField]
	private ObscuredString m_oldName;

	[SerializeField]
	private ObscuredInt m_SkipOption;

	[SerializeField]
	private ObscuredString m_key;

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

	public string Code
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string oldName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int SkipOption
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string key
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
