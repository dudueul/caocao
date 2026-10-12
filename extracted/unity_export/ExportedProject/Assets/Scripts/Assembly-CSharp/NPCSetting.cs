using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class NPCSetting : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_setUnitLv;

	[SerializeField]
	private List<ObscuredInt> m_setUnitLvNum;

	[SerializeField]
	private ObscuredInt m_treasureEnhanceLv;

	[SerializeField]
	private ObscuredInt m_unitPassiveNum;

	[SerializeField]
	private ObscuredInt m_battleFormationLv;

	[SerializeField]
	private ObscuredInt m_trainNum;

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

	public List<ObscuredInt> setUnitLv
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> setUnitLvNum
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int treasureEnhanceLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int unitPassiveNum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int battleFormationLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int trainNum
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
