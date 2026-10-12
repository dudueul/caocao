using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class BattleFormation : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_width;

	[SerializeField]
	private ObscuredInt m_height;

	[SerializeField]
	private ObscuredInt m_frontLine;

	[SerializeField]
	private ObscuredInt m_middleLine;

	[SerializeField]
	private ObscuredInt m_rearLine;

	[SerializeField]
	private ObscuredInt m_front;

	[SerializeField]
	private ObscuredInt m_middle;

	[SerializeField]
	private ObscuredInt m_rear;

	[SerializeField]
	private List<ObscuredInt> m_formationIdx;

	[SerializeField]
	private ObscuredInt m_goodType;

	[SerializeField]
	private ObscuredInt m_cost;

	[SerializeField]
	private List<ObscuredInt> m_effectType;

	[SerializeField]
	private List<ObscuredInt> m_effectTarget;

	[SerializeField]
	private List<ObscuredInt> m_effectAttribute;

	[SerializeField]
	private List<ObscuredInt> m_effectValue;

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

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

	public int width
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int height
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int frontLine
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int middleLine
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rearLine
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int front
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int middle
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rear
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> formationIdx
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int goodType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cost
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectType
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectTarget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectAttribute
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> effectValue
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
