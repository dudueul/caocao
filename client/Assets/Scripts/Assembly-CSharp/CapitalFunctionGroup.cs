using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class CapitalFunctionGroup : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_foodFunction;

	[SerializeField]
	private ObscuredInt m_silverFunction;

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

	public int foodFunction
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silverFunction
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
