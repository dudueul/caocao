using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class AutoText : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredString m_txt;

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

	public int type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string txt
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
