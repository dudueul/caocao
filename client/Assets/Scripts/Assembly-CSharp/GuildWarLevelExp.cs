using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarLevelExp : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_corpsNeedExp;

	[SerializeField]
	private ObscuredInt m_corpsExpSum;

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

	public int corpsNeedExp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int corpsExpSum
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
