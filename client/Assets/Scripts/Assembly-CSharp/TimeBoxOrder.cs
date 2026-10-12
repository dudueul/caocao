using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TimeBoxOrder : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_boxId;

	[SerializeField]
	private ObscuredInt m_nextId;

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

	public int boxId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int nextId
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
