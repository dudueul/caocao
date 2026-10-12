using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Passive : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_inheritId;

	[SerializeField]
	private ObscuredInt m_triggerType;

	[SerializeField]
	private ObscuredInt m_triggerTileValue;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredString m_atlas;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_iconDeco;

	[SerializeField]
	private ObscuredInt m_groupingCriterion;

	[SerializeField]
	private ObscuredString m_postFixEffect;

	[SerializeField]
	private ObscuredString m_applyEffect;

	[SerializeField]
	private ObscuredInt m_waitApplyEffect;

	[SerializeField]
	private ObscuredString m_effect;

	[SerializeField]
	private ObscuredInt m_showIconInGame;

	[SerializeField]
	private ObscuredInt m_aiTarget;

	[SerializeField]
	private ObscuredInt m_displayType;

	[SerializeField]
	private ObscuredInt m_aiBonusType;

	[SerializeField]
	private ObscuredInt m_atkVal;

	[SerializeField]
	private ObscuredInt m_tier;

	[SerializeField]
	private ObscuredInt m_silver;

	[SerializeField]
	private ObscuredInt m_gold;

	[SerializeField]
	private ObscuredInt m_mainBaseStat;

	[SerializeField]
	private ObscuredInt m_divisorVal;

	[SerializeField]
	private ObscuredString m_shortestDesc;

	[SerializeField]
	private ObscuredString m_desc;

	[SerializeField]
	private ObscuredInt m_accumulate;

	[SerializeField]
	private List<ObscuredInt> m_passiveId;

	[SerializeField]
	private ObscuredInt m_triggerWeatherValue;

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

	public int inheritId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int triggerType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int triggerTileValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string atlas
	{
		get
		{
			return null;
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

	public string iconDeco
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int groupingCriterion
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string postFixEffect
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string applyEffect
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int waitApplyEffect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string effect
	{
		get
		{
			return null;
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

	public int aiTarget
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int displayType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int aiBonusType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int atkVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int tier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silver
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int gold
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mainBaseStat
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int divisorVal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string shortestDesc
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

	public int accumulate
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> passiveId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int triggerWeatherValue
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
