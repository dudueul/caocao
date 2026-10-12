using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class SaveFlag : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_useType;

	[SerializeField]
	private ObscuredInt m_isUseBattle;

	[SerializeField]
	private ObscuredInt m_useScenarioId;

	[SerializeField]
	private ObscuredInt m_orderInScenario;

	[SerializeField]
	private ObscuredInt m_scenarioFlag;

	[SerializeField]
	private ObscuredInt m_value0_Ambition;

	[SerializeField]
	private ObscuredInt m_value1_Ambition;

	[SerializeField]
	private ObscuredInt m_value2_Ambition;

	[SerializeField]
	private ObscuredInt m_value3_Ambition;

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

	public int useType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isUseBattle
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int useScenarioId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int orderInScenario
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int scenarioFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value0_Ambition
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value1_Ambition
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value2_Ambition
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value3_Ambition
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
