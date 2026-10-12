using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class NPCName : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_stringType;

	[SerializeField]
	private ObscuredString m_stringData;

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

	public int stringType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string stringData
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
