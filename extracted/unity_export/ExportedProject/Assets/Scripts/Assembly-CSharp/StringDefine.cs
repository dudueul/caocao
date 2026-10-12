using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class StringDefine : PermanentBase
{
	[SerializeField]
	private ObscuredString m_txt;

	[SerializeField]
	private ObscuredInt m_id;

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
}
