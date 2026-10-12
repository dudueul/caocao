using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class DigitValue : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_townAttackFood;

	[SerializeField]
	private ObscuredInt m_townReconFood;

	[SerializeField]
	private ObscuredInt m_captialSilver;

	[SerializeField]
	private ObscuredInt m_userChangeSilver;

	[SerializeField]
	private ObscuredInt m_attackedTime;

	[SerializeField]
	private ObscuredInt m_attackedRate;

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

	public int townAttackFood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int townReconFood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int captialSilver
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int userChangeSilver
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int attackedTime
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int attackedRate
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
