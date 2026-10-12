using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Sound : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredString> m_path;

	[SerializeField]
	private ObscuredInt m_substitute;

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

	public List<ObscuredString> path
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int substitute
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
