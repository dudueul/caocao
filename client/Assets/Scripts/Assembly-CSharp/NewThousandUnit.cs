using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class NewThousandUnit : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_UnitID;

	[SerializeField]
	private List<ObscuredInt> m_jobtype;

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

	public List<ObscuredInt> UnitID
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> jobtype
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
