using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TownFunction : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_needLordLV;

	[SerializeField]
	private ObscuredInt m_needCastleLV;

	[SerializeField]
	private ObscuredInt m_needSilver;

	[SerializeField]
	private ObscuredInt m_needFood;

	[SerializeField]
	private ObscuredInt m_resultLordExp;

	[SerializeField]
	private ObscuredInt m_resultType;

	[SerializeField]
	private ObscuredInt m_resultValue;

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

	public int needLordLV
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needCastleLV
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

	public int needFood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int resultLordExp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int resultType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int resultValue
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
