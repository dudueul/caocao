using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class StartRandomUnit : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_unitTreeNodeID;

	[SerializeField]
	private ObscuredInt m_ratio;

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

	public int unitTreeNodeID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int ratio
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
