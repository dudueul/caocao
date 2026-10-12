using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Vip : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_needVipPoint;

	[SerializeField]
	private ObscuredInt m_vipPointSum;

	[SerializeField]
	private ObscuredInt m_achievementID;

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

	public int needVipPoint
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int vipPointSum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int achievementID
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
