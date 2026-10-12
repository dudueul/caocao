using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Condition : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_conditionType;

	[SerializeField]
	private ObscuredInt m_buffType;

	[SerializeField]
	private ObscuredInt m_isStack;

	[SerializeField]
	private ObscuredInt m_showIconInGame;

	[SerializeField]
	private ObscuredInt m_minEffect;

	[SerializeField]
	private ObscuredInt m_cancelType;

	[SerializeField]
	private ObscuredInt m_cancelCondition;

	[SerializeField]
	private ObscuredInt m_prevCondition;

	[SerializeField]
	private ObscuredInt m_auraPassive;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_color;

	[SerializeField]
	private ObscuredString m_prefab;

	[SerializeField]
	private ObscuredInt m_triggerMarker;

	[SerializeField]
	private ObscuredString m_marker;

	[SerializeField]
	private ObscuredInt m_viewType;

	[SerializeField]
	private List<ObscuredString> m_markerName;

	[SerializeField]
	private List<ObscuredInt> m_viewLoc;

	[SerializeField]
	private ObscuredString m_castSE;

	[SerializeField]
	private ObscuredString m_clearSE;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private List<ObscuredInt> m_stackVal;

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

	public int conditionType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int buffType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int isStack
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int showIconInGame
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int minEffect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cancelType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cancelCondition
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int prevCondition
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int auraPassive
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string icon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string color
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string prefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int triggerMarker
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string marker
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int viewType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredString> markerName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> viewLoc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string castSE
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string clearSE
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string desc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> stackVal
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
