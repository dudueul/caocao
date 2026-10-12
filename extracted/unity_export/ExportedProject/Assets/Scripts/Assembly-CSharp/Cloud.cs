using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Cloud : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_posX;

	[SerializeField]
	private ObscuredInt m_posY;

	[SerializeField]
	private ObscuredInt m_needLordLevel;

	[SerializeField]
	private ObscuredInt m_needSilver;

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

	public int posX
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int posY
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needLordLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needSilver
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
